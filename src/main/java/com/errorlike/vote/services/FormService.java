package com.errorlike.vote.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.errorlike.vote.entities.Form;
import com.errorlike.vote.repositories.FormRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class FormService {
    private final FormRepository formRepository;
    public Form createNewForm(Form form) {
        form.setCreateTime(LocalDateTime.now().withNano(0));
        return formRepository.save(form);
    }

    public List<Form> getForms() {
        List<Form> forms = formRepository.findAll();
        return forms;
    }
}
