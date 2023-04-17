package com.errorlike.vote.controllers;

import com.errorlike.vote.entities.Form;
import com.errorlike.vote.services.FormService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(FormController.class)
@RequiredArgsConstructor
public class FormControllerTest {
    private final MockMvc mockMvc;
    @MockBean
    private FormService formService;

    @Test
    void testCreate() throws Exception {

        LocalDateTime now = LocalDateTime
                .now()
                .withNano(0);
        Form
                .builder()
                .createTime(now)
                .id(1L)
                .name("testQuestion")
                .duration(25)
                .build();


        mockMvc.perform(post("/forms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "testQuestion",
                                  "duration": 25,
                                  "questions": [
                                    {
                                      "questionType": 1,
                                      "name": "testQuestion",
                                      "questionOptions": [
                                        {
                                          "name": "testOption"
                                        }
                                      ]
                                    }
                                  ]
                                }
                                """)
                )
                .andExpect(result -> {
                });
    }

    @Test
    void testGetAll() {

    }
}
