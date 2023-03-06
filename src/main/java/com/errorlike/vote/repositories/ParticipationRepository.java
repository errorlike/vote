package com.errorlike.vote.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.errorlike.vote.entities.Participation;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

}