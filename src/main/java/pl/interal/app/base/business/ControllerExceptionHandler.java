package pl.interal.app.base.business;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import pl.interal.app.base.business.exceptions.EmailExistException;
import pl.interal.app.base.business.exceptions.PasswordNotSameException;
import pl.interal.app.base.business.exceptions.UserNotFoundException;
import pl.interal.app.base.dto.ErrorMessage;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerExceptionHandler {


	private static final String ERROR_MSG_PASSWORD_NOT_SAME = "Password are not same";
	private static final String USER_NOT_FOUND_MSG = "User not found in db ";

	@ExceptionHandler(value = {PasswordNotSameException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage resourceNotFoundException(PasswordNotSameException ex, WebRequest request) {
		return new ErrorMessage(
				HttpStatus.BAD_REQUEST.value(),
				LocalDateTime.now(),
				ex.getMessage(),
				ERROR_MSG_PASSWORD_NOT_SAME);
	}


	@ExceptionHandler(value = {UserNotFoundException.class})
	@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
	public ErrorMessage userNotFoundExceptionHandler(PasswordNotSameException ex, WebRequest request) {
		return new ErrorMessage(
				HttpStatus.I_AM_A_TEAPOT.value(),
				LocalDateTime.now(),
				ex.getMessage(),
				USER_NOT_FOUND_MSG);
	}

	@ExceptionHandler(value = {EmailExistException.class})
	@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
	public ErrorMessage emailExists(EmailExistException ex, WebRequest request) {
		return new ErrorMessage(
				HttpStatus.BAD_REQUEST.value(),
				LocalDateTime.now(),
				ex.getMessage(),
				"");
	}

}
