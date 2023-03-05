package pl.interal.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.interal.app.base.business.LoginService;
import pl.interal.app.base.business.UserUpdateService;
import pl.interal.app.base.dto.LoginDto;
import pl.interal.app.responses.LogInDtoResponse;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Tag(name = "LoginController", description = "Log in controller")
@RequestMapping("/api/auth")
public class LoginController {

	private final LoginService loginService;

	private final UserUpdateService userUpdateService;


	@Operation(summary = "Log in controller")
	@PostMapping
	public ResponseEntity<LogInDtoResponse> logIn(@Valid @RequestBody LoginDto body) {
		final LogInDtoResponse logInDtoResponse = loginService.logIn(body.getEmail(), body.getPassword());
		return ResponseEntity.ok(logInDtoResponse);
	}


	@GetMapping("/reset")
	public ResponseEntity<Void> getNewPassword(@RequestParam String email) {
		userUpdateService.resetPassword(email);
		return ResponseEntity.ok().build();
	}

}
