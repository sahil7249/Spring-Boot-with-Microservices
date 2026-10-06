package com.service.auth.service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.service.auth.dto.UserDto;
import com.service.auth.entity.User;
import com.service.auth.exception.UserNotFoundException;
import com.service.auth.exception.EmailAlreadyExistsException;
import com.service.auth.repository.UserRepository;
import com.service.auth.dto.RegisterDto;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UserService {
    private final UserRepository userRepository;

    private User getLoggedInUser(){
        String email = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication().getName());
        return userRepository.findByEmail(email).orElseThrow(() -> 
            new UserNotFoundException("User not found with email : "+ email )
        );
    }

    public UserDto getCurrentUser() {
        return map(getLoggedInUser());
    }

    public UserDto updateUser(RegisterDto dto) {
        User user = getLoggedInUser();

        if(!user.getEmail().equals(dto.getEmail()) && userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Email already in use");
        }

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return map(user);
    }   

    public List<UserDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    public UserDto getUserById(UUID  id) {
        return map(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id :" + id)));
    }

    public void deleteUserById(UUID id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id :" + id));
        userRepository.delete(user);
    }

    private UserDto map(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRole());
    }
}
