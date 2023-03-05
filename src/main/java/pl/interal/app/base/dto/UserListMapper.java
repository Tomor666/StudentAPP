package pl.interal.app.base.dto;

import org.mapstruct.Mapper;
import pl.interal.app.responses.UserResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserListMapper {

	List<UserResponse> fromUserDtoList(List<UserDto> dto);


	default UserResponse mapUserDto(UserDto userDto) {
		return UserResponse.builder()
						   .email(userDto.getEmail())
						   .id(userDto.getId())
						   .userName(userDto.getUserName())
						   .createdAt(userDto.getCreatedAt())
						   .isEnabled(userDto.isEnabled()).build();
	}


}
