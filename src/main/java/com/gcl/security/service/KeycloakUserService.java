package com.gcl.security.service;

import org.keycloak.representations.idm.UserRepresentation;

import dto.UserDTO;
import dto.UserListDTO;

import java.util.List;
import java.util.Map;

public interface KeycloakUserService {

	UserListDTO findAllUsers(Map<String, String> filter);

	List<UserRepresentation> searchUserByUsername(String username);

	String createUser(UserDTO userDTO);

	void deleteUser(String userId);

	void updateUser(String userId, UserDTO userDTO);
}
