package com.minita.security.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.minita.security.model.User;

@EnableMongoRepositories
public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByUsername(String username);
	
	

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);


}
