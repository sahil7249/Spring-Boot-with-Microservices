package com.service.auth.dto;

import java.util.UUID;

import com.service.auth.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor 
@NoArgsConstructor 
@Getter
@Setter 
public class UserDto {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private Role role;
}
