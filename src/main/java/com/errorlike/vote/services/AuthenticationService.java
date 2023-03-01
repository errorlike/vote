package com.errorlike.vote.services;

import com.errorlike.vote.configs.JwtUtil;
import com.errorlike.vote.entities.User;
import com.errorlike.vote.models.SecurityUser;
import com.errorlike.vote.repositories.UserRepository;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authorizationManager;
    private final JwtUtil jwtUtil;

    private final JpaUserDetailService jpaUserDetailService;

    public void register(User user) {
        userRepository.save(user);
    }

    public Map<String, Object> login(String username, String password) {
        authorizationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User user = userRepository.findByUsername(username).orElseThrow();
        SecurityUser userDetails = new SecurityUser(user);
        String accessToken = jwtUtil.generateToken(userDetails);
        String refreshToken = jwtUtil.generateToken(Map.of("uuid", UUID.randomUUID().toString()), userDetails,
                1000 * 60 * 60 * 2);
        var userMap = new HashMap<String, Object>();
        userMap.put("user", user);
        userMap.put("accessToken", accessToken);
        userMap.put("refreshToken", refreshToken);
        return userMap;
    }

    // todo accessToken and refreshToken has different vaild

    public Map<String, String> refreshToken(String refreshToken) {
        try {
            String username = jwtUtil.extractUsername(refreshToken);
            UserDetails userDetails = jpaUserDetailService.loadUserByUsername(username);
            if (jwtUtil.isTokenValid(refreshToken, userDetails)) {
                String accessToken = jwtUtil.generateToken(userDetails);
                String newRefreshToken = jwtUtil.generateToken(Map.of("uuid", UUID.randomUUID().toString()),
                        userDetails, 1000 * 60 * 60 * 2);
                return Map.of("accessToken", accessToken,
                        "refreshToken", newRefreshToken);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "refreshToken is  Expired");
            }
        } catch (SignatureException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "refreshToken is invalid");
        }
    }

}
