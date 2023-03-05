package pl.interal.app.base.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.interal.app.responses.LogInDtoResponse;
import pl.interal.app.security.JwtUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;


    public LogInDtoResponse logIn(String email, String password) {
        final Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        final String jwt = jwtUtils.generateJwtToken(authenticate);
        return new LogInDtoResponse(jwt, 200);
    }
}
