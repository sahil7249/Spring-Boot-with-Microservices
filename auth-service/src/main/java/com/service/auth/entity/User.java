package com.service.auth.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Setter
@Getter
@AllArgsConstructor 
@NoArgsConstructor 
@Table(name="users")
public class User {
    
    @Id 
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Column(unique=true,nullable=false)
    private String name;

    @Column(unique=true,nullable=false)
    private String email;

    @Column(nullable=false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
