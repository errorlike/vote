
package com.errorlike.vote.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.errorlike.vote.entities.Question;
import com.errorlike.vote.services.QuestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forms/{id}/questions")
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody(required = true) List<Question> questions,
            @PathVariable("id") long id) {
        List<Question> savedQuestions = questionService.createQuestions(questions, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestions);
    }

}
