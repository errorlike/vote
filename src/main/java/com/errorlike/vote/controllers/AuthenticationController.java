package com.errorlike.vote.controllers;

import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.errorlike.vote.entities.User;
import com.errorlike.vote.models.RegisterUser;
import com.errorlike.vote.models.loginRequest;
import com.errorlike.vote.services.AuthenticationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterUser registerUser) {
        User user = User.builder()
                .age(registerUser.getAge())
                .username(registerUser.getUsername())
                .passwordHash(passwordEncoder.encode(registerUser.getPassword()))
                .email(registerUser.getEmail())
                .createTime(LocalDateTime.now().withNano(0))
                .build();
        try {
            authenticationService.register(user);

        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "重复的用户名或邮箱", e);
        }
        // todo chang body content to message
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody loginRequest loginRequest) {
        Map<String, Object> userMap = authenticationService.login(loginRequest.getUsername(),
                loginRequest.getPassword());
        return ResponseEntity.ok(userMap);
    }

}
