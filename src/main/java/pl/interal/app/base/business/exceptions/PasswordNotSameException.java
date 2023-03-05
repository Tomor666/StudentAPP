package pl.interal.app.base.business.exceptions;

public class PasswordNotSameException extends RuntimeException {

	public PasswordNotSameException(String message){
		super(message);
	}
}
