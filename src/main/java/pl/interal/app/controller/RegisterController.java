package pl.interal.app.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.interal.app.base.business.UserRegisterService;
import pl.interal.app.base.business.exceptions.PasswordNotSameException;
import pl.interal.app.base.data.Roles;
import pl.interal.app.base.dto.CreateNewUserDto;
import pl.interal.app.base.dto.UserCreateDto;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/auth/register")
@RestController
public class RegisterController {

	private final UserRegisterService userRegisterService;


	@PostMapping
	public ResponseEntity<Void> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
		validatePassword(userCreateDto.getPassword(),userCreateDto.getRepeatedPassword());
		userRegisterService.registerNewUserAccount(CreateNewUserDto.builder()
				.userName(userCreateDto.getUserName())
				.roles(Roles.ROLE_USER)
				.email(userCreateDto.getEmail())
				.password(userCreateDto.getPassword()).build());

		return ResponseEntity.accepted().build();
	}


	private void validatePassword(String password, String repeatedPassword) {
		if(!password.trim().equals(repeatedPassword.trim())){
			throw new PasswordNotSameException("Password not same error");
		}
	}


}
