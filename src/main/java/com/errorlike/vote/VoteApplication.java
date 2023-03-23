package com.errorlike.vote;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.errorlike.vote.entities.User;
import com.errorlike.vote.repositories.UserRepository;
import com.github.javafaker.Faker;

@SpringBootApplication
public class VoteApplication {
    public static void main(String[] args) {
        SpringApplication.run(VoteApplication.class, args);
    }

    @Bean
    Faker faker() {
        return new Faker();
    }

    @Bean
    CommandLineRunner dataLoader(Faker faker, UserRepository userRepository, PasswordEncoder passwordEncoder) {

        return args -> {
            if (userRepository.count() <= 100) {
                List<User> users = IntStream.rangeClosed(1, 100)
                        .mapToObj(i -> new User(null,
                                faker.name().fullName(),
                                passwordEncoder.encode("123456"),
                                faker.random().nextInt(1, 120),
                                faker.internet().emailAddress(),
                                LocalDateTime.now().withNano(0)))
                        .toList();
                userRepository.saveAll(users);
            }
        };
    }

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("REQUEST DATA: ");
        return filter;
    }
}
