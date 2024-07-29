package dto;

import java.util.List;
import java.util.Set;

import org.keycloak.representations.idm.UserRepresentation;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;


@Value
@RequiredArgsConstructor
@Builder
public class UserListDTO {
	private List<UserRepresentation> content;
	private Integer totalElements;
	
}
