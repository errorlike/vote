package com.errorlike.vote.services;

import java.util.HashMap;
import java.util.Map;

import com.errorlike.vote.configs.JwtUtil;
import com.errorlike.vote.entities.User;
import com.errorlike.vote.models.SecurityUser;
import com.errorlike.vote.repositories.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authorizationManager;
    private final JwtUtil jwtUtil;

    public void register(User user) {
        userRepository.save(user);
    }

    public Map<String, Object> login(String username, String password) {
        authorizationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User user = userRepository.findByUsername(username).orElseThrow();
        String generateToken = jwtUtil.generateToken(new SecurityUser(user));
        var userMap = new HashMap<String, Object>();
        userMap.put("user", user);
        userMap.put("token", generateToken);
        return userMap;
    }
}
