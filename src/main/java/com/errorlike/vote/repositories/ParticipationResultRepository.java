package com.errorlike.vote.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.errorlike.vote.entities.ParticipationResult;
import com.errorlike.vote.entities.ParticipationResultKey;

public interface ParticipationResultRepository extends JpaRepository<ParticipationResult, ParticipationResultKey> {
    List<ParticipationResult> findByParticipationId(Long id);



}