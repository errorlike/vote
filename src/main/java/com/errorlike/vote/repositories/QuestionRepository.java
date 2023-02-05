package com.errorlike.vote.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.errorlike.vote.entities.Question;

public interface QuestionRepository extends JpaRepository<Question,Long>{
    
}
