package com.example.spring.security.dtos;

import com.example.spring.security.enums.UserRole;
import lombok.Data;

@Data
public class SignUpDto {

    private String login;
    private String password;
    UserRole role;
}
