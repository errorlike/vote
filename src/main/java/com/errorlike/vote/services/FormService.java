package com.errorlike.vote.services;

import java.time.LocalDateTime;
import java.util.List;

import com.errorlike.vote.entities.Question;
import com.errorlike.vote.entities.QuestionOption;
import com.errorlike.vote.repositories.QuestionOptionRepository;
import com.errorlike.vote.repositories.QuestionRepository;
import org.springframework.data.domain.PageRequest;
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
    private final QuestionRepository questionRepository;
    private final QuestionOptionRepository questionOptionRepository;
    public Form createNewForm(Form form) {
        form.setCreateTime(LocalDateTime.now().withNano(0));
        Form savedForm = formRepository.save(form);
        List<Question> questions = form.getQuestions();
        if (questions!=null && questions.size() !=0) {
            questions.forEach(question -> {
                question.setForm(form);
                List<QuestionOption> questionOptions = question.getQuestionOptions();
                if(questionOptions !=null && questionOptions.size()!=0){
                    questionOptions.forEach(questionOption -> {questionOption.setQuestion(question);});
                }
            });
            questionRepository.saveAll(questions);
        }
        return savedForm;
    }

    public List<Form> getForms() {
        List<Form> forms = formRepository.findAll();
        return forms;
    }
}
