package pl.interal.app.responses;

import lombok.Value;

@Value
public class LogInDtoResponse {

	String jwtToken;

	int statusCode;
}
