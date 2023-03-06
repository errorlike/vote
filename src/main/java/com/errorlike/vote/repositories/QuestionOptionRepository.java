package com.errorlike.vote.repositories;

import com.errorlike.vote.entities.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Long> {
    List<QuestionOption> findByIdIn(List<Long> ids);

}
