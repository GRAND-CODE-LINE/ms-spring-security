package com.gcl.security.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcl.dental.core.dto.ResponseDto;
import com.gcl.dental.core.model.security.User;
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
	public ResponseEntity<ResponseDto> create(@RequestBody User user) {
		System.out.println("create, mundo!");
		user.setPassword(encoder.encode(user.getPassword()));
		ResponseDto dto = new ResponseDto();

		try {
			dto.setBody(userService.save(user));
			return new ResponseEntity<>(dto, HttpStatus.OK);

		} catch (Exception e) {
			dto.setError(e.getMessage());
			return new ResponseEntity<>(dto, HttpStatus.OK);

		}
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

	@CrossOrigin
	@DeleteMapping("{id}")
	public void delete(@PathVariable String id) {
		System.out.println("create, mundo!");
		User user = userService.getById(id).get();
		userService.delete(user);
	}

}
