package com.errorlike.vote.repositories;

import com.errorlike.vote.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long>{

}


