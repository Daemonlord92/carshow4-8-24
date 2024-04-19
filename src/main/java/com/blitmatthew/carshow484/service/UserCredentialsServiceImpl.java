package com.blitmatthew.carshow484.service;

import com.blitmatthew.carshow484.config.JwtService;
import com.blitmatthew.carshow484.entity.UserCredentials;
import com.blitmatthew.carshow484.repository.UserCredentialRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsServiceImpl implements UserCredentialService{
    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserCredentials userCredentials) {
        userCredentials.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
        userCredentialRepository.save(userCredentials);
    }

    @Override
    public String login(UserCredentials userCredentials) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredentials.getUsername(), userCredentials.getPassword()));
        userCredentials = userCredentialRepository.findByEmail(userCredentials.getUsername()).orElseThrow(() -> new EntityNotFoundException("Invalid Login"));
        String jwt = jwtService.generateToken(userCredentials.getUsername());
        return jwt;
    }
}
