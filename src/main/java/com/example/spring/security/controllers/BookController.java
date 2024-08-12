package com.example.spring.security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    @GetMapping
    public ResponseEntity<List<String>> findAll() {
        return ResponseEntity.ok(List.of("book1", "book2", "book3"));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody String book) {
        return ResponseEntity.ok(book);
    }
}
