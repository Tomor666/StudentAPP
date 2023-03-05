package pl.interal.app.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class AppPasswordEncoder implements PasswordEncoder {


	@Override
	public String encode(CharSequence rawPassword) {
		return DigestUtils.sha256Hex(rawPassword.toString());
	}


	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		final String passwordInMD5 = DigestUtils.md5Hex(rawPassword.toString());
		final String sha256 = DigestUtils.sha256Hex(passwordInMD5);
		return sha256.equals(encodedPassword);
	}
}
