package com.errorlike.vote.entities;

import com.errorlike.vote.utils.QuestionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private QuestionType questionType;
    @Column(length = 50)
    @NotBlank(message = "The name cannot be empty")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "question", orphanRemoval = true)
    private List<QuestionOption> questionOptions;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "form_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    @ToStringExclude
    private Form form;
}
