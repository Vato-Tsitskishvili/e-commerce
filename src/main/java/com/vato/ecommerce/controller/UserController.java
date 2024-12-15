package com.vato.ecommerce.controller;

import com.vato.ecommerce.exceptions.UserNotFoundException;
import com.vato.ecommerce.model.entity.User;
import com.vato.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHeader(@RequestHeader("Authorization") String jwt) throws UserNotFoundException {
        User user = userService.findUserProfileByJwt(jwt);

        return ResponseEntity.ok(user);
    }
}
