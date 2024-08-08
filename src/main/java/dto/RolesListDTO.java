package dto;

import java.util.List;

import org.keycloak.representations.idm.RoleRepresentation;



import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;


@Value
@RequiredArgsConstructor
@Builder
public class RolesListDTO {
	private List<RoleRepresentation> content;
	private Integer totalElements;
	
}
