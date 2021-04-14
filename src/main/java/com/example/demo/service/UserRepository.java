package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
	/**
	 * Returns all user from Database
	 *
	 * @return list of all users
	 */
	List<User> findAll();

	/**
	 * Returns user from Database by ID
	 *
	 * @param id User's ID
	 * @return Optional with user if exists or null if not.
	 */
	Optional<User> findById(long id);

	/**
	 * Save user in Database
	 *
	 * @param user which should be saved.
	 * @return saved user from Database with generated ID
	 */
	User save(User user);

	/**
	 * Delete user from Database
	 *
	 * @param user which should be deleted.
	 */
	void delete(User user);
}
