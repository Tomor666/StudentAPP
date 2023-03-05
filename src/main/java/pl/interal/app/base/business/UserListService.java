package pl.interal.app.base.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.interal.app.base.data.User;
import pl.interal.app.base.dto.UserDto;
import pl.interal.app.base.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserListService {

	private final UserRepository userRepository;


	public List<UserDto> findAllUsers(Integer page, Integer size) {
		if (page < 0 || size < 0) {
			final List<User> all = userRepository.findAll();
			return all.stream().map(user -> UserDto.builder()
												   .email(user.getEmail())
												   .userName(user.getUsername())
												   .userRole(user.getRole().getAuthority().toString())
												   .createdAt(user.getCreatedAt())
												   .id(user.getId())
												   .build())
					  .collect(Collectors.toList());

		}
		Pageable pageable = PageRequest.of(page, size);
		final Page<User> pageableUsers = userRepository.findAll(pageable);
		log.info("Fetched user with pageable");
		return pageableUsers.get()
							.map(user -> UserDto.builder()
												.email(user.getEmail())
												.userName(user.getUsername())
												.createdAt(user.getCreatedAt())
												.userRole(user.getRole().getAuthority().toString())
												.id(user.getId())
												.build())
							.collect(Collectors.toList());

	}
}
