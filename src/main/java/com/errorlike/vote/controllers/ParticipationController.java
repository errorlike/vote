package com.errorlike.vote.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.errorlike.vote.entities.Participation;
import com.errorlike.vote.entities.ParticipationResult;
import com.errorlike.vote.models.ParticipationRequest;
import com.errorlike.vote.models.SecurityUser;
import com.errorlike.vote.services.FormService;
import com.errorlike.vote.services.ParticipationResultService;
import com.errorlike.vote.services.ParticipationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/participations")
@RequiredArgsConstructor
public class ParticipationController {
    private final ParticipationService participationService;

    private final ParticipationResultService participationResultService;

    @PostMapping()
    public ResponseEntity<Participation> create(@RequestBody ParticipationRequest participationRequest) {
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        long userId = securityUser.getId();
        Participation createdParticipation = participationService
                .createParticipation(participationRequest.getFormId(), userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdParticipation);
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        List<Participation> participations = participationService.getAll();
        return ResponseEntity.ok(participations);

    }

    @PostMapping("/{participationId}/participationResults")
    public ResponseEntity<?> createParticipationResults(@PathVariable long participationId, @RequestBody List<Long> questionOptionIds) {
        List<ParticipationResult> participationResults = participationResultService.createAll(participationId, questionOptionIds);

        return ResponseEntity.status(HttpStatus.CREATED).body(participationResults);

    }
}
