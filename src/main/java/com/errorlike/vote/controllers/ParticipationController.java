package com.errorlike.vote.controllers;

import com.errorlike.vote.dtos.ParticipationWithFormIdProjection;
import com.errorlike.vote.entities.Participation;
import com.errorlike.vote.entities.ParticipationResult;
import com.errorlike.vote.models.ParticipationRequest;
import com.errorlike.vote.models.SecurityUser;
import com.errorlike.vote.services.ParticipationResultService;
import com.errorlike.vote.services.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> get(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            List<ParticipationWithFormIdProjection> participationWithFormIds = participationService.getByUserId(userId);
            return ResponseEntity.ok(participationWithFormIds);
        } else {
            List<Participation> participations = participationService.getAll();
            return ResponseEntity.ok(participations);
        }

    }

    @PostMapping("/{participationId}/participationResults")
    public ResponseEntity<?> createParticipationResults(@PathVariable long participationId, @RequestBody List<Long> questionOptionIds) {
        List<ParticipationResult> participationResults = participationResultService.createAll(participationId, questionOptionIds);
        return ResponseEntity.status(HttpStatus.CREATED).body(participationResults);
    }

}
