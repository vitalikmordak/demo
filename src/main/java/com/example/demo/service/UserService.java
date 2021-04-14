package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.NoUserFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	/**
	 * Returns all users from Database
	 *
	 * @return list of all users
	 */
	public List<User> findAll() {
		return userRepository.findAll();
	}

	/**
	 * Creates a new user in Database.
	 * User's password will be encrypted and store.
	 *
	 * @param user which should be created
	 * @return created user
	 */
	@Transactional
	public User create(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		return userRepository.save(user);
	}

	/**
	 * Delete user from Database
	 *
	 * @param user which should be deleted
	 */
	@Transactional
	public void delete(User user) {
		userRepository.delete(user);
	}

	/**
	 * Returns user from Database by ID
	 *
	 * @param userId User's ID
	 * @return user if exists.
	 * @throws NoUserFoundException when user not found by specified ID
	 */
	public User findById(long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new NoUserFoundException(userId));
	}
}
