package com.service.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.service.auth.dto.RegisterDto;
import com.service.auth.dto.RegisterResponseDto;
import com.service.auth.dto.LoginDto;
import com.service.auth.dto.LoginResponseDto;
import com.service.auth.entity.User;
import com.service.auth.exception.DuplicateUserException;
import com.service.auth.exception.UserNotFoundException;
import com.service.auth.exception.EmailAlreadyExistsException;
import com.service.auth.repository.UserRepository;
import com.service.auth.security.JwtService;


import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Transactional 
    public RegisterResponseDto register(RegisterDto dto) { 
        String email = dto.getEmail();
        if(userRepository.existsByEmail(email)) {
            String response = String.format("User exists with give email : %s , try another email", email);
            throw new EmailAlreadyExistsException(response);
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        
        User savedUser = userRepository.save(user);
        return new RegisterResponseDto(savedUser.getId(),email);
    }

    public LoginResponseDto login(LoginDto dto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> 
            new UserNotFoundException("User not found after authentication")
        );
        return new LoginResponseDto(user.getEmail(),jwtService.generateJwtToken(user));
    }

}
