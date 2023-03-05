package pl.interal.app.base.dto;


import lombok.ToString;
import lombok.Value;
import pl.interal.app.base.data.Roles;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Value
public class UserCreateDto {

	@NotNull
	@Email
	String email;

	@NotNull
	String userName;

	@NotNull
	@ToString.Exclude
	String password;

	@NotNull
	@ToString.Exclude
	String repeatedPassword;

}
