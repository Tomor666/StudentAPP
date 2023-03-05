package pl.interal.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.interal.app.base.business.UserListService;
import pl.interal.app.base.dto.UserDto;
import pl.interal.app.base.dto.UserListMapper;
import pl.interal.app.base.dto.UserResponseMapper;
import pl.interal.app.responses.UserResponse;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/users")
@RestController
public class UserListController {

	private final UserListService userListService;

	private final UserListMapper userListMapper;


	@GetMapping
	public ResponseEntity<List<UserResponse>> getAllUserPagable(@RequestParam Integer page, Integer size) {
		final List<UserDto> allUsers = userListService.findAllUsers(page, size);
		final List<UserResponse> convert = UserResponseMapper.convert(allUsers);
		final List<UserResponse> usersResponse = userListMapper.fromUserDtoList(allUsers);
		return ResponseEntity.ok(convert);
	}
}
