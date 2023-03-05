package pl.interal.app.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.interal.app.base.business.UserUpdateService;
import pl.interal.app.base.dto.UserDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@RequestMapping("api/manage")
@RestController
public class UserManageController {


    private final UserUpdateService userUpdateService;

    @GetMapping
    public ResponseEntity<UserDto> getUsers(@RequestParam Integer id){
        final UserDto user = userUpdateService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest) {
        userUpdateService.updateUser(updateUserRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUserById(@RequestParam String id) {
        Long longUserId = Long.valueOf(id);

        userUpdateService.deleteUser(longUserId);
        return ResponseEntity.ok().build();
    }



}
