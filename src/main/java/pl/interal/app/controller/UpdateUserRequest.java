package pl.interal.app.controller;


import lombok.Value;
import pl.interal.app.base.data.Roles;

import javax.management.relation.Role;
import javax.validation.constraints.NotNull;

@Value
public class UpdateUserRequest {

    @NotNull
    Long userId;

    String password;

    String repeatedPassword;

    String role;

    String userName;

    String email;

}
