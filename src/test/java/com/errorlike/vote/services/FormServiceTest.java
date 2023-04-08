package com.errorlike.vote.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.errorlike.vote.entities.Form;
import com.errorlike.vote.entities.Question;
import com.errorlike.vote.entities.QuestionOption;
import com.errorlike.vote.repositories.FormRepository;
import com.errorlike.vote.repositories.QuestionRepository;
import com.errorlike.vote.utils.QuestionType;

@ExtendWith(MockitoExtension.class)
public class FormServiceTest {
    @Mock
    private FormRepository formRepository;
    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private FormService formService;

    @Test
    void testCreateNewForm() {
        QuestionOption questionOption = QuestionOption
                .builder()
                .name("testQuestionOption")
                .build();
        Question question = Question.builder()
                .name("testQuestion")
                .questionType(QuestionType.RADIO)
                .questionOptions(List.of(questionOption))
                .build();
        Form form = Form.builder()
                .name("test")
                .duration(20)
                .questions(List.of(question))
                .build();
        when(formRepository.save(any(Form.class))).thenReturn(form);
        when(questionRepository.saveAll(anyList())).then(AdditionalAnswers.returnsFirstArg());

        Form savedForm = formService.createNewForm(form);

        assertEquals(form.getName(), savedForm.getName());
        assertIterableEquals(form.getQuestions(), savedForm.getQuestions());
        assertIterableEquals(question.getQuestionOptions(), savedForm.getQuestions().get(0).getQuestionOptions());
        assertNotNull(savedForm.getCreateTime());
    }

    @Test
    void testGetForms() {

    }
}
