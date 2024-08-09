package com.gcl.security.service;

import util.KeycloakProvider;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import dto.RoleDTO;
import dto.RolesListDTO;
import java.util.Map;

@Service

public class KeycloakRoleServiceImpl implements KeycloakRoleService {
	/**
	 * Metodo para listar todos los usuarios de Keycloak
	 * 
	 * @return List<UserRepresentation>
	 */
	public RolesListDTO findAllRoles(Map<String, String> filters) {
		
		Integer page = filters.get("page") != null ? Integer.parseInt(filters.get("page")) : 0;

		Integer size = filters.get("size") != null ? Integer.parseInt(filters.get("size")) : 10;

		RolesListDTO rolesListDTO = new RolesListDTO(KeycloakProvider.getRealmResource().roles().list(page, size),
				KeycloakProvider.getRealmResource().roles().list().size());

		return rolesListDTO;
	}

	/**
	 * Metodo para buscar un usuario por su username
	 * 
	 * @return List<UserRepresentation>
	 */
	public RoleRepresentation searchRoleById(String id) {

		return KeycloakProvider.getRealmResource().rolesById().getRole(id);
	}

	/**
	 * Metodo para crear un usuario en keycloak
	 * 
	 * @return String
	 */
	public void createRole(@NonNull RoleDTO roleDTO) {

		RolesResource rolesResource = KeycloakProvider.getRealmResource().roles();
		RoleRepresentation roleRepresentation = new RoleRepresentation();
		roleRepresentation.setName(roleDTO.getName());
		roleRepresentation.setDescription(roleDTO.getDescription());
		roleRepresentation.setComposite(true);
		rolesResource.create(roleRepresentation);
	}

	/**
	 * Metodo para borrar un usuario en keycloak
	 * 
	 * @return void
	 */
	public void deleteRole(String roleName) {
		KeycloakProvider.getRealmResource().roles().deleteRole(roleName);
	}

	/**
	 * Metodo para actualizar un usuario en keycloak
	 * 
	 * @return void
	 */
	public void updateRole(String roleName, @NonNull RoleDTO userDTO) {
		RoleRepresentation roleRepresentation = new RoleRepresentation();
		roleRepresentation.setDescription(userDTO.getDescription());
		roleRepresentation.setComposite(true);
		roleRepresentation.setName(userDTO.getName());

		RolesResource rolesResource = KeycloakProvider.getRealmResource().roles();
		rolesResource.get(userDTO.getName()).update(roleRepresentation);

	}
}
