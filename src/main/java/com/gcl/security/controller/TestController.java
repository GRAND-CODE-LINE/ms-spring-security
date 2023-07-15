package com.gcl.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcl.security.model.Role;
import com.gcl.security.repository.RoleRepository;

@RestController
@RequestMapping("/api/test")
public class TestController {
	@Autowired
	RoleRepository roleRepository;
	@GetMapping("/all")
	public List<Role> allAccess() {
		return roleRepository.findAll();
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public List<Role> moderatorAccess() {
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		return roleRepository.findAll();
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}