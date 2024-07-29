package com.gcl.security.service;

import org.keycloak.representations.idm.RoleRepresentation;


import dto.RoleDTO;
import dto.RolesListDTO;
import java.util.Map;

public interface KeycloakRoleService {

	RolesListDTO findAllRoles(Map<String, String> filter);

	RoleRepresentation searchRoleById(String id);

	void createRole(RoleDTO roleDTO);

	void deleteRole(String roleName);

	void updateRole(String userId, RoleDTO roleDTO);
}
