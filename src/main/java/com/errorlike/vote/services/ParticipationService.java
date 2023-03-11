package com.errorlike.vote.services;

import com.errorlike.vote.dtos.ParticipationWithFormId;
import com.errorlike.vote.entities.Form;
import com.errorlike.vote.entities.Participation;
import com.errorlike.vote.entities.User;
import com.errorlike.vote.repositories.FormRepository;
import com.errorlike.vote.repositories.ParticipationRepository;
import com.errorlike.vote.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    private final FormRepository formRepository;
    private final UserRepository userRepository;

    public Participation createParticipation(long formId, long userId) {
        Optional<Form> form = formRepository.findById(formId);
        Optional<User> user = userRepository.findById(userId);
        Participation participation = Participation.builder()
                .user(user.orElseThrow())
                .form(form.orElseThrow())
                .participationTime(LocalDateTime.now().withNano(0))
                .build();
        return participationRepository.save(participation);
    }

    public List<Participation> getAll() {
        return participationRepository.findAll();
    }

    public List<ParticipationWithFormId> getByUserId(long userId) {
        return participationRepository.findParticipationWithFormIdByUserId(userId);
    }
}
