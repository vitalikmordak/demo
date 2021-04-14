package com.example.demo.infrastructure.repository;

import com.example.demo.entity.User;
import com.example.demo.service.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring JPA implementation of {@link UserRepository}
 */
public interface SpringUserRepository extends JpaRepository<User, Long>, UserRepository {
}
