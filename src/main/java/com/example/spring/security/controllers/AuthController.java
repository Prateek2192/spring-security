package com.example.spring.security.controllers;

import com.example.spring.security.config.auth.JwtTokenService;
import com.example.spring.security.dtos.JwtDto;
import com.example.spring.security.dtos.SignInDto;
import com.example.spring.security.dtos.SignUpDto;
import com.example.spring.security.entities.User;
import com.example.spring.security.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthService authService;

    @Autowired
    JwtTokenService jwtTokenService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDto dto) {
        UserDetails user = authService.signUp(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user.getUsername() + " user created");
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtDto> signIn(@RequestBody @Valid SignInDto dto) {
        UsernamePasswordAuthenticationToken userNameAndPasswordAuthentication = new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword());
        Authentication authUser = authenticationManager.authenticate(userNameAndPasswordAuthentication);
        String accessToken = jwtTokenService.generateAccessToken((User) authUser.getPrincipal());

        return ResponseEntity.ok(new JwtDto(accessToken));
    }

}
