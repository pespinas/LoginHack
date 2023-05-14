package com.hackathon.utils;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.hackathon.models.Authority;
import com.hackathon.models.User;
import com.hackathon.repositories.AuthorityRepository;
import com.hackathon.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Runner implements CommandLineRunner {
	private final UserRepository userRepository;
	private final AuthorityRepository authorityRepository;

	public Runner(UserRepository userRepository, AuthorityRepository authorityRepository) {
		this.userRepository = userRepository;
		this.authorityRepository = authorityRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		if (this.authorityRepository.count() == 0) {
			this.authorityRepository.saveAll(List.of(new Authority(AuthorityName.ADMIN), 
					new Authority(AuthorityName.READ),
					new Authority(AuthorityName.WRITE) 
			));
			log.info("Authorities values was added!");
		}

		if (this.userRepository.count() == 0) {
			// Creamos los usuarios:
			this.userRepository.save(new User("Pau", new BCryptPasswordEncoder().encode("123"),
					(List<Authority>) List.of(this.authorityRepository.findByName(AuthorityName.READ).get())));
			this.userRepository.save(new User("Horumis", new BCryptPasswordEncoder().encode("123"),
					(List<Authority>) List.of(this.authorityRepository.findByName(AuthorityName.WRITE).get())));
			this.userRepository.save(new User("Pedro", new BCryptPasswordEncoder().encode("123"),
					(List<Authority>) List.of(this.authorityRepository.findByName(AuthorityName.ADMIN).get())));
			log.info("Users & relational tabled (N:M) values was added!");
		}
	}
}
