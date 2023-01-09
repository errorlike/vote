package com.errorlike.vote.repositories;

import java.util.Optional;

import com.errorlike.vote.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
