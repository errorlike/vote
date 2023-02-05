package com.errorlike.vote.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.errorlike.vote.entities.Form;

public interface FormRepository extends JpaRepository<Form, Long> {
}
