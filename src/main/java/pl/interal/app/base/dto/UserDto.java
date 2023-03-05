package pl.interal.app.base.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class UserDto {

	LocalDateTime createdAt;

	boolean isEnabled;

	String userName;

	String email;

	String userRole;

	Long id;
}
