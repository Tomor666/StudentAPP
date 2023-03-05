package pl.interal.app.base.dto;

import lombok.Builder;
import lombok.Data;
import pl.interal.app.base.data.Roles;

@Data
@Builder
public class CreateNewUserDto {

	String userName;
	String email;
	String password;
	Roles roles;

}
