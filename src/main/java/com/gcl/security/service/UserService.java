package com.gcl.security.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;


import com.gcl.security.model.User;

public interface UserService {

	User save(User user);

	User edit(User user);

	void delete(User user);

	Optional<User> getById(String id);
	
	Page<User> paginate(Map<String, String> filters);

}
