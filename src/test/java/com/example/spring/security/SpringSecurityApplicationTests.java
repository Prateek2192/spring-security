package com.example.spring.security;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringSecurityApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    TestH2Repository testH2Repository;

    private static RestTemplate restTemplate;
    private String baseUrl = "http://localhost";

    @BeforeAll
    public static void initBeforeAll() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void initBeforeEach() {
        baseUrl = baseUrl.concat(":").concat(String.valueOf(port)).concat("api/v1/books");
    }

}
