package com.vato.ecommerce.service;

import com.vato.ecommerce.exceptions.EmailAlreadyExistsException;
import com.vato.ecommerce.jwt.JwtUtils;
import com.vato.ecommerce.model.dto.AuthResponse;
import com.vato.ecommerce.model.dto.LoginRequest;
import com.vato.ecommerce.model.entity.User;
import com.vato.ecommerce.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;
    private final CartService cartService;

    public AuthService(
            UserRepository userRepository,
            JwtUtils jwtUtils,
            PasswordEncoder passwordEncoder,
            CustomUserDetailsService customUserDetailsService,
            CartService cartService) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
        this.cartService = cartService;
    }

    public AuthResponse createUser(User user) throws EmailAlreadyExistsException {
        Optional<User> userExists = userRepository.findByEmail(user.getEmail());

        if (userExists.isPresent())
            throw new EmailAlreadyExistsException("User with such email already exists");

        User createdUser = User.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();

        User savedUser = userRepository.save(createdUser);
        cartService.createCart(savedUser);
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );
        String token = jwtUtils.generateTokenFromUsername((UserDetails) authentication.getPrincipal());

        return new AuthResponse(token, "Signup Success");
    }

    public AuthResponse loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticate(loginRequest.email(), loginRequest.password());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateTokenFromUsername((UserDetails) authentication.getPrincipal());

        return new AuthResponse(jwt, "Login Success");
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException("Invalid username or password");

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }
}
