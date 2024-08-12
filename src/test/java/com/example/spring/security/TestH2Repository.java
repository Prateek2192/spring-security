package com.example.spring.security;

import com.example.spring.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<User, Long> {
}
