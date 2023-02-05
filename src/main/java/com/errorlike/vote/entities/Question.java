package com.errorlike.vote.entities;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.errorlike.vote.utils.QuestionType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private QuestionType questionType;
    @Column(length = 50)
    @NotBlank(message = "The name cannot be empty")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinColumn(name = "question_option_id", referencedColumnName = "id")
    private List<QuestionOption> questionOptions;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "form_id", referencedColumnName = "id")
    @JsonIgnore
    private Form form;
}
