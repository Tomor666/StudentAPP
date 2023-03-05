package pl.interal.app.base.dto;

import pl.interal.app.responses.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

public class UserResponseMapper {


	public static List<UserResponse> convert(List<UserDto> userDtoList) {

		return userDtoList.stream()
						  .map(userDto -> UserResponse.builder()
													  .userName(userDto.getUserName())
													  .id(userDto.getId())
													  .role(userDto.getUserRole())
													  .email(userDto.getEmail())
													  .createdAt(userDto.getCreatedAt()).build())
						  .collect(Collectors.toList());

	}
}
