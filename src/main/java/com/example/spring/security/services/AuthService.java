package com.example.spring.security.services;

import com.example.spring.security.dtos.SignUpDto;
import com.example.spring.security.entities.User;
import com.example.spring.security.exceptions.InvalidJwtException;
import com.example.spring.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

    public UserDetails signUp(SignUpDto dto) {

        if (userRepository.findByLogin(dto.getLogin()) != null) {
            throw new InvalidJwtException("User login exists");
        }

        String encryptedPwd = new BCryptPasswordEncoder().encode(dto.getPassword());
        User newUser = new User(dto.getLogin(), encryptedPwd, dto.getRole());

        return userRepository.save(newUser);
    }
}
