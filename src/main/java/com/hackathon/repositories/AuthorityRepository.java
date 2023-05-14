package com.hackathon.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.models.Authority;
import com.hackathon.utils.AuthorityName;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	// Creamos un m�todo que nos retonar� una autoridad por su nombre
	Optional<Authority> findByName(AuthorityName name);
}
