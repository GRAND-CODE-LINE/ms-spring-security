package com.gcl.security.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcl.security.model.User;

import com.gcl.security.service.UserService;

@RestController
@RequestMapping("/api/user/")
public class UserController {
	@Autowired
	private UserService userService;
	

	@Autowired
	PasswordEncoder encoder;

	@CrossOrigin
	@PostMapping("paginate")
	Page<User> paginate(@RequestBody Map<String, String> filter) {
		System.out.println("Hola, mundo!");
		return userService.paginate(filter);
	}

	@CrossOrigin
	@PostMapping("")
	public User create(@RequestBody User user) {
		System.out.println("create, mundo!");
		user.setPassword(encoder.encode(user.getPassword())); 
		return userService.save(user);
	}

	@CrossOrigin
	@GetMapping("{id}")
	public Optional<User> getById(@PathVariable String id) {
		System.out.println("create, mundo!");

		return userService.getById(id);
	}
	
	
	@CrossOrigin
	@PutMapping("{id}")
	public User update(@PathVariable String id, @RequestBody User user) {
		System.out.println("create, mundo!");
		user.setPassword(encoder.encode(user.getPassword())); 
		return userService.edit(user);
	}
	

}
