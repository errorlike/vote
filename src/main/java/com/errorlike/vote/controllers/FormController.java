package com.errorlike.vote.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.errorlike.vote.entities.Form;
import com.errorlike.vote.services.FormService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/forms")
@RequiredArgsConstructor
public class FormController {
    private final FormService formService;

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody Form form) {
        Form savedForm = formService.createNewForm(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedForm);
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(formService.getForms());
    }
    

}
