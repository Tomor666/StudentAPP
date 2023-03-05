package pl.interal.app.base;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebApplicationConsts {
	public static final String API_PREFIX = "/";
	public static final ResponseEntity<Void> NO_CONTENT = ResponseEntity.noContent().build();
	public static final ResponseEntity<Void> UNAUTHORIZED = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	public static final ResponseEntity<Void> BAD_REQUEST = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	public static final ResponseEntity<Void> TOO_MANY_REQUESTS = ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
}
