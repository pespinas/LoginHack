package com.hackathon.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	// Vamos a definir un m�todo que busque si existe un usuario por su nombre de
	Optional<User> findByUsername(String username);
}
