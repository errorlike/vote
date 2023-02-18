package com.errorlike.vote.repositories;

import com.errorlike.vote.entities.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;
public interface QuestionOptionRepository extends JpaRepository<QuestionOption,Long> {
    
}
