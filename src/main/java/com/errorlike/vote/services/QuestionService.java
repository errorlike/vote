package com.errorlike.vote.services;

import com.errorlike.vote.entities.Form;
import com.errorlike.vote.entities.Question;
import com.errorlike.vote.repositories.FormRepository;
import com.errorlike.vote.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final FormRepository formRepository;

    public List<Question> createQuestions(List<Question> questions, long formId) {
        Optional<Form> form = formRepository.findById(formId);
        questions.forEach(question -> question.setForm(form.orElseThrow()));
        return questionRepository.saveAll(questions);
    }

    public List<Question> getByFormId(long formId) {
        return questionRepository.findByFormId(formId);
    }

    public Question getQuestionById(long id) {
        Optional<Question> question = questionRepository.findById(id);
        return question.orElseThrow();
    }
}
