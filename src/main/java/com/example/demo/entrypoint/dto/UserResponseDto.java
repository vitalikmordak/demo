package com.example.demo.entrypoint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO for user fetched from Database
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

	private long id;

	private String firstName;

	private String lastName;

	private GeoDataResponseDto geoData;
}
