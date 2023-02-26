
package com.errorlike.vote.controllers;

import com.errorlike.vote.entities.Question;
import com.errorlike.vote.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forms/{formId}/questions")
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody(required = true) List<Question> questions,
            @PathVariable("formId") long id) {
        List<Question> savedQuestions = questionService.createQuestions(questions, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestions);
    }

    @GetMapping(value = "/{questionId}")
    public ResponseEntity<?> getQuestionById(@PathVariable("questionId") long id) {

        Question question = questionService.getQuestionById(id);
        return ResponseEntity.ok(question);
    }

}
