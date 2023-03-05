package pl.interal.app.base.dto;

import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Value
public class LoginDto {

    @NotNull
    @Email
    String email;

    @NotNull
    @ToString.Exclude
    String password;
}
