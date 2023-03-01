package com.errorlike.vote.repositories;

import com.errorlike.vote.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long>{
    List<Question> findByFormId(Long formId);

}


