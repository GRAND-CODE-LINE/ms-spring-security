package com.gcl.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.gcl.security.service.KeycloakRoleService;
import com.gcl.security.service.KeycloakUserService;

import dto.RoleDTO;
import dto.UserDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
@RequestMapping("/keycloak/role")
public class KeycloakRoleController {

	@Autowired
	private KeycloakRoleService keycloakService;

	@CrossOrigin
	@PostMapping("/search")
	public ResponseEntity<?> findAllUsers(@RequestBody Map<String, String> filter) {
		return ResponseEntity.ok(keycloakService.findAllRoles(filter));
	}

	@GetMapping("/search/{id}")
	public ResponseEntity<?> searchUserByUsername(@PathVariable String id) {
		return ResponseEntity.ok(keycloakService.searchRoleById(id));
	}

	@CrossOrigin
	@PostMapping(value = "/create", consumes = { "*/*" })
	public ResponseEntity<?> createUser(@RequestBody RoleDTO roleDTO) throws URISyntaxException {
		keycloakService.createRole(roleDTO);
		return ResponseEntity.ok(null);

	}

	@CrossOrigin
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody RoleDTO roleDTO) {
		keycloakService.updateRole(id, roleDTO);
		return ResponseEntity.ok(null);
	}

	@CrossOrigin
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable String id) {
		keycloakService.deleteRole(id);
		return ResponseEntity.noContent().build();
	}
}