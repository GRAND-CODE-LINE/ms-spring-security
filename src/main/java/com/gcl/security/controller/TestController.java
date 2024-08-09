package com.gcl.security.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


@RestController
@EnableMethodSecurity
public class TestController {
	
	@CrossOrigin
	@GetMapping("/hello-1")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloAdmin(){
        return "Hello Sprig Boot With Keycloak with ADMIN";
    }

	@CrossOrigin
    @GetMapping("/hello-2")
    //@PreAuthorize("hasRole('USER_ROLE') or hasRole('ADMIN_ROLE')")
    public String helloUser() throws JsonProcessingException{
    	   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	   ObjectMapper objectMapper = new ObjectMapper();
    	   objectMapper.registerModule(new JavaTimeModule());
    	   String jsonString = objectMapper.writeValueAsString(authentication.getPrincipal());
    	   System.out.println(jsonString);
    	   System.out.println("authentication: " + authentication.getPrincipal().toString());
           if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
               UserDetails userDetails = (UserDetails) authentication.getPrincipal();
               String role = userDetails.getAuthorities().iterator().next().getAuthority();
               System.out.println("Rol del token: " + role);
           }
        return "Hello Sprig Boot With Keycloak with USER";
    }
	

}