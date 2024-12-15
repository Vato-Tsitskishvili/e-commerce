package com.vato.ecommerce.service;

import com.vato.ecommerce.exceptions.UserNotFoundException;
import com.vato.ecommerce.jwt.JwtUtils;
import com.vato.ecommerce.model.entity.User;
import com.vato.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public UserService(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    public User findUserById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));
    }

    public User findUserProfileByJwt(String jwt) throws UserNotFoundException {
        String email = jwtUtils.getUsernameFromJwtToken(jwt);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));
    }
}
