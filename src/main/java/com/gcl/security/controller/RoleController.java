package com.gcl.security.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcl.security.model.Role;
import com.gcl.security.service.RoleService;



@RestController
@RequestMapping("/api/role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@CrossOrigin
	@PostMapping("paginate")
	Page<Role> paginate(@RequestBody Map<String, String> filter) {
		  System.out.println("Hola, mundo!");
		return roleService.paginate(filter);
	}

}
