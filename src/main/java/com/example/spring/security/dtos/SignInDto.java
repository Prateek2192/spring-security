package com.example.spring.security.dtos;

import lombok.Data;

@Data
public class SignInDto {

    private String login;
    private String password;
}
