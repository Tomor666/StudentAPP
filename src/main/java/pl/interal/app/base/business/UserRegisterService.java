package pl.interal.app.base.business;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.interal.app.base.business.exceptions.EmailExistException;
import pl.interal.app.base.data.Authorities;
import pl.interal.app.base.data.User;
import pl.interal.app.base.dto.CreateNewUserDto;
import pl.interal.app.base.mail.EmailService;
import pl.interal.app.base.repository.AuthoritiesRepository;
import pl.interal.app.base.repository.UserRepository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserRegisterService {

	private final UserRepository userRepository;

	private final AuthoritiesRepository authoritiesRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final EmailService emailService;

	private final ExecutorService executor = Executors.newFixedThreadPool(1);


	public void registerNewUserAccount(CreateNewUserDto createNewUserDto) {
		checkUserEmailExists(createNewUserDto.getEmail());
		final User user = buildUser(createNewUserDto);
		final User save = userRepository.save(user);
		authoritiesRepository.save(Authorities.builder()
											  .id(save.getId().intValue())
											  .authority(createNewUserDto.getRoles())
											  .build());


		log.info("User  with name {}  and email {} created and committed", user.getUsername(), user.getEmail());
		sendEmailAsync(save);
	}


	private void checkUserEmailExists(String email) {
		final Optional<User> byEmail = userRepository.findByEmail(email);
		byEmail.ifPresent(user -> {
			log.error("Given email {} is stored in DB ", user.getEmail());
			throw new EmailExistException("Email exists in DB..");
		});
	}


	private void sendEmailAsync(User save) {
		CompletableFuture.runAsync(() -> emailService.sendAndEmail(save.getEmail(), "Register success"), executor).
						 exceptionally(this::logErrorWhenAsyncThrowsError);
	}


	private Void logErrorWhenAsyncThrowsError(Throwable t) {
		log.error("Encountered exception during handle: {}, {}", t.getClass().getSimpleName(), t.getMessage());
		return null;
	}


	private User buildUser(CreateNewUserDto createNewUserDto) {

		return User.builder()
				   .username(createNewUserDto.getUserName())
				   .email(createNewUserDto.getEmail())
				   .enabled(false)
				   .password(bCryptPasswordEncoder.encode(createNewUserDto.getPassword())).build();

	}

}
