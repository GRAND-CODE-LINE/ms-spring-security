package dto;

import lombok.*;
import java.io.Serializable;


@Value
@RequiredArgsConstructor
@Builder
public class RoleDTO implements Serializable {
	private String id;
	private String name;
	private String description;
	
}
