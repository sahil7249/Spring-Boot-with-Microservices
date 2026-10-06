package com.service.auth.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.auth.dto.UserDto;
import com.service.auth.dto.RegisterDto;
import com.service.auth.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor 
public class UserController {

    private final UserService userService;
    
    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe() {
        return ResponseEntity.ok().body(userService.getCurrentUser());
    }

    @PutMapping("/me")
    public ResponseEntity<UserDto> updateMe(@RequestBody RegisterDto dto) {
        return ResponseEntity.ok().body(userService.updateUser(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id ) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id ) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
