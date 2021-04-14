package com.example.demo.entrypoint;

import com.example.demo.entity.GeoData;
import com.example.demo.entity.User;
import com.example.demo.entrypoint.dto.UserRequestDto;
import com.example.demo.entrypoint.dto.UserResponseDto;
import com.example.demo.infrastructure.converter.UserConverter;
import com.example.demo.service.GeoDataService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User APIs
 */
@RestController
@RequestMapping(value = "api/v1/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final GeoDataService geoDataService;

	/**
	 * Endpoint for getting all users from Database
	 *
	 * @return list of users
	 */
	@GetMapping
	public List<UserResponseDto> getAll() {
		return userService.findAll().stream()
				.map(UserConverter::convert)
				.collect(Collectors.toList());
	}

	/**
	 * Endpoint for creating a new user
	 *
	 * @param requestDto payload for creating new user.
	 * @return created user from Database with generated ID.
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponseDto create(@RequestBody UserRequestDto requestDto) {
		GeoData geoData = geoDataService.findById(requestDto.getGeoDataId());

		User candidate = UserConverter.convert(requestDto);
		candidate.setGeoData(geoData);
		User created = userService.create(candidate);

		return UserConverter.convert(created);
	}

	/**
	 * Endpoint for deleting user by ID.
	 * Returns 204 (No Content) status if deletion is successful.
	 * If user not found response status will be 404 (Not Found).
	 *
	 * @param id User's ID
	 */
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long id) {
		User user = userService.findById(id);
		userService.delete(user);
	}
}
