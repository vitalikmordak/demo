package com.example.demo.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Logging request and response with their payloads.
 */
@Slf4j
@Component
public class CustomLoggingFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		doFilterWrapped(wrapRequest(request), wrapResponse(response), filterChain);
	}

	protected void doFilterWrapped(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, FilterChain filterChain) throws ServletException, IOException {
		StringBuilder msg = new StringBuilder();

		try {
			beforeRequest(request, msg);
			filterChain.doFilter(request, response);
		} finally {
			afterRequest(request, response, msg);
			log.info(msg.toString());
			response.copyBodyToResponse();
		}
	}

	protected void beforeRequest(ContentCachingRequestWrapper request, StringBuilder msg) {
		msg.append(String.format("\n[REQUEST][%s][%s]", request.getMethod(), request.getRequestURI()));
	}

	protected void afterRequest(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, StringBuilder msg) {
		logRequestBody(request, msg);
		msg.append("\n[RESPONSE]");
		logResponse(response, msg);
	}

	private static void logRequestBody(ContentCachingRequestWrapper request, StringBuilder msg) {
		byte[] content = request.getContentAsByteArray();
		if (content.length > 0) {
			logContent(content, request.getCharacterEncoding(), msg);
		}
	}

	private static void logResponse(ContentCachingResponseWrapper response, StringBuilder msg) {
		int status = response.getStatus();
		msg.append(String.format("[%s %s]", status, HttpStatus.valueOf(status).getReasonPhrase()));
		byte[] content = response.getContentAsByteArray();
		if (content.length > 0) {
			logContent(content, response.getCharacterEncoding(), msg);
		}
	}

	private static void logContent(byte[] content, String contentEncoding, StringBuilder msg) {
		try {
			String contentString = new String(content, contentEncoding);
			msg.append("\n").append(contentString);
		} catch (UnsupportedEncodingException e) {
			msg.append(String.format("[%d bytes content]", content.length)).append("\n");
		}
	}

	private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
		if (request instanceof ContentCachingRequestWrapper) {
			return (ContentCachingRequestWrapper) request;
		} else {
			return new ContentCachingRequestWrapper(request);
		}
	}

	private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
		if (response instanceof ContentCachingResponseWrapper) {
			return (ContentCachingResponseWrapper) response;
		} else {
			return new ContentCachingResponseWrapper(response);
		}
	}
}
