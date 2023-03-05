package pl.interal.app.base.business;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.interal.app.base.business.exceptions.PasswordNotSameException;
import pl.interal.app.base.business.exceptions.UserNotFoundException;
import pl.interal.app.base.data.Authorities;
import pl.interal.app.base.data.Roles;
import pl.interal.app.base.data.User;
import pl.interal.app.base.dto.UserDto;
import pl.interal.app.base.mail.EmailService;
import pl.interal.app.base.repository.AuthoritiesRepository;
import pl.interal.app.base.repository.UserRepository;
import pl.interal.app.controller.UpdateUserRequest;

import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserUpdateService {

	private final UserRepository userRepository;

	private final AuthoritiesRepository authoritiesRepository;


	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final EmailService emailService;
	private final ExecutorService executor = Executors.newFixedThreadPool(1);


	public UserDto getUser(Integer id) {
		final Optional<User> optionalUser = userRepository.findById(Long.valueOf(id));
		if (optionalUser.isPresent()) {
			final User user = optionalUser.get();
			return UserDto.builder()
						  .userRole(user.getRole().getAuthority().toString())
						  .createdAt(user.getCreatedAt())
						  .userName(user.getUsername())
						  .email(user.getEmail()).build();
		}

		log.error("User does not exists");
		throw new UserNotFoundException("User not found by id ");
	}


	@Transactional
	public void updateUser(UpdateUserRequest userRequest) {
		checkPasswordAreSame(userRequest);
		Optional<User> optionalUser = userRepository.findById(userRequest.getUserId());
		optionalUser.ifPresentOrElse(user -> {
			log.info("Start updating user with id number {}", user.getId());

			if (userRequest.getPassword() != null && !userRequest.getPassword().isEmpty()) {
				user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
			}

			if (userRequest.getUserName() != null && !userRequest.getUserName().isEmpty()) {
				user.setUsername(userRequest.getUserName());
			}


			if (userRequest.getEmail() != null && !userRequest.getEmail().isEmpty()) {
				user.setEmail(userRequest.getEmail());
			}


			if (userRequest.getRole() != null && !userRequest.getRole().isEmpty()) {
				user.setRole(Authorities.builder().authority(Roles.valueOf(userRequest.getRole())).build());
			}

		}, () -> {
			log.error("User does not exists");
			throw new UserNotFoundException("User not found by id ");
		});
	}


	private void checkPasswordAreSame(UpdateUserRequest userRequest) {
		if (!userRequest.getPassword().equals(userRequest.getRepeatedPassword())) {
			throw new PasswordNotSameException("Password not same.... ");
		}
	}


	public void deleteUser(Long longUserId) {
		log.info("Deleting user with id  {}", longUserId);
		final Optional<User> byId1 = userRepository.findById(longUserId);
		if (byId1.isPresent()) {
			authoritiesRepository.delete(byId1.get().getRole());
			userRepository.delete(byId1.get());
		}

	}


	@Transactional
	public void resetPassword(String email) {
		final Optional<User> byEmail = userRepository.findByEmail(email);
		if (byEmail.isPresent()) {
			log.info("User exist in DB and we will set an new password");
			int leftLimit = 48; // numeral '0'
			int rightLimit = 122; // letter 'z'
			int targetStringLength = 10;
			Random random = new Random();

			String generatedString = random.ints(leftLimit, rightLimit + 1)
					.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
					.limit(targetStringLength)
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
					.toString();
			final User user = byEmail.get();
			user.setPassword(bCryptPasswordEncoder.encode(generatedString));
			userRepository.save(user);
			CompletableFuture.runAsync(() -> emailService.sendAndEmail(user.getEmail(), generatedString), executor).
					exceptionally(this::logErrorWhenAsyncThrowsError);

		}

	}
	private Void logErrorWhenAsyncThrowsError(Throwable t) {
		log.error("Encountered exception during handle: {}, {}", t.getClass().getSimpleName(), t.getMessage());
		return null;
	}
}
