package pl.interal.app.responses;


import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@Value
@Builder
public class UserResponse implements Serializable {

	LocalDateTime createdAt;

	boolean isEnabled;

	String userName;

	String email;

	String role;

	Long id;
}
