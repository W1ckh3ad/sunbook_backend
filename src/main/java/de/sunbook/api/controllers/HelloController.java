package de.sunbook.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.sunbook.api.models.AuthenticationRequestModel;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping("/post")
    public ResponseEntity<?> post(@RequestBody AuthenticationRequestModel request) {
        return ResponseEntity.ok("Hello Post");
    }
}
