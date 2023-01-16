package com.errorlike.vote.repositories;

import com.errorlike.vote.entities.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByUsername() {
        User user = userRepository.findByUsername("yyx1232").get();
        System.out.println(user);
    }
}
