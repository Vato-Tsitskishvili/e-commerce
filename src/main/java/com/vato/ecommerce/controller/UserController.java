package com.vato.ecommerce.controller;

import com.vato.ecommerce.exceptions.UserNotFoundException;
import com.vato.ecommerce.model.entity.User;
import com.vato.ecommerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get User Profile Header")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)
                    )
            )
    })
    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHeader(@RequestHeader("Authorization") String jwt) throws UserNotFoundException {
        User user = userService.findUserProfileByJwt(jwt);

        return ResponseEntity.ok(user);
    }
}
