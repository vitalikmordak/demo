package com.example.demo.infrastructure.converter;

import com.example.demo.entity.User;
import com.example.demo.entrypoint.dto.UserRequestDto;
import com.example.demo.entrypoint.dto.UserResponseDto;
import lombok.experimental.UtilityClass;

/**
 * Converter for user to and from DTOs
 */
@UtilityClass
public final class UserConverter {
	/**
	 * Convert user from request payload.
	 *
	 * @param requestDto payload with user's data to create.
	 * @return User entity with converted data.
	 */
	public static User convert(UserRequestDto requestDto) {
		return new User(
				requestDto.getFirstName(),
				requestDto.getLastName(),
				requestDto.getPassword()
		);

	}

	/**
	 * Convert user entity to response DTO
	 *
	 * @param user which should be converted to DTO
	 * @return converted response DTO
	 */
	public static UserResponseDto convert(User user) {
		return UserResponseDto.builder()
				.id(user.getId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.geoData(GeoDataConverter.convert(user.getGeoData()))
				.build();
	}
}
