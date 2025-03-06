package com.vato.ecommerce.controller;

import com.vato.ecommerce.exceptions.EmailAlreadyExistsException;
import com.vato.ecommerce.model.dto.AuthResponse;
import com.vato.ecommerce.model.dto.LoginRequest;
import com.vato.ecommerce.model.entity.User;
import com.vato.ecommerce.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Sign Up")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Use created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Failed to create user"
            )
    })
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUser(@RequestBody User user) throws EmailAlreadyExistsException {
        return new ResponseEntity<>(authService.createUser(user), HttpStatus.CREATED);
    }

    @Operation(summary = "Log In")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Use logged in successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized Access"
            )
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.loginUser(loginRequest));
    }
}
