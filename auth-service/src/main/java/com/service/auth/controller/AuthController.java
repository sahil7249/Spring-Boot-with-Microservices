package com.service.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.auth.service.AuthService;
import com.service.auth.dto.RegisterResponseDto;
import com.service.auth.dto.RegisterDto;
import com.service.auth.dto.LoginDto;
import com.service.auth.dto.LoginResponseDto;

import lombok.RequiredArgsConstructor;


@RestController 
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor 
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> registerUser(@RequestBody  RegisterDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginDto dto) {
        return ResponseEntity.ok().body(authService.login(dto));
    }
}
