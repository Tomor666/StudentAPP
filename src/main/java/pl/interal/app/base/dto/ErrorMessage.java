package pl.interal.app.base.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ErrorMessage {

	int statusCode;
	LocalDateTime timestamp;
	String message;
	String description;
}
