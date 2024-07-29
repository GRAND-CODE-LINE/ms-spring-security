package com.gcl.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.gcl.security.service.KeycloakUserService;

import dto.UserDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
@RequestMapping("/keycloak/user")
public class KeycloakUserController {

	@Autowired
	private KeycloakUserService keycloakService;

	@CrossOrigin
	@PostMapping("/search")
	public ResponseEntity<?> findAllUsers(@RequestBody Map<String, String> filter) {
		return ResponseEntity.ok(keycloakService.findAllUsers(filter));
	}

	@GetMapping("/search/{username}")
	public ResponseEntity<?> searchUserByUsername(@PathVariable String username) {
		return ResponseEntity.ok(keycloakService.searchUserByUsername(username));
	}

	@CrossOrigin
	@PostMapping(value = "/create", consumes = { "*/*" })
	public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) throws URISyntaxException {
		String response = keycloakService.createUser(userDTO);
		return ResponseEntity.created(new URI("/keycloak/user/create")).body(response);

	}

	@CrossOrigin
	@PutMapping("/update/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UserDTO userDTO) {
		keycloakService.updateUser(userId, userDTO);
		return ResponseEntity.ok("User updated successfully");
	}

	@CrossOrigin
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable String userId) {
		keycloakService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}
}