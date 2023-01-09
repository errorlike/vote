package com.errorlike.vote.repositories;

import com.errorlike.vote.entities.Form;

import org.springframework.data.jpa.repository.JpaRepository;

interface FormRepository extends JpaRepository<Form, Long> {
}
