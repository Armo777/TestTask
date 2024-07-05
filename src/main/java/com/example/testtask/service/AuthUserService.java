package com.example.testtask.service;

import com.example.testtask.entity.AuthUser;
import com.example.testtask.repositories.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {

    private final AuthUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthUserService(AuthUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthUser save(AuthUser authUser) {
        // Хеширование пароля перед сохранением в базу данных
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        return userRepository.save(authUser);
    }

    public boolean matchesPassword(AuthUser authUser, String password) {
        return passwordEncoder.matches(password, authUser.getPassword());
    }

    public AuthUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
