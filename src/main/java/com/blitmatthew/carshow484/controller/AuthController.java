package com.blitmatthew.carshow484.controller;

import com.blitmatthew.carshow484.entity.UserCredentials;
import com.blitmatthew.carshow484.service.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserCredentialService userCredentialService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserCredentials userCredentials) {
        return ResponseEntity.ok(userCredentialService.login(userCredentials));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserCredentials userCredentials) {
        userCredentialService.createUser(userCredentials);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
