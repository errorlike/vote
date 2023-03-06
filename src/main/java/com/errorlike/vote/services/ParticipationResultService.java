package com.errorlike.vote.services;

import com.errorlike.vote.entities.Participation;
import com.errorlike.vote.entities.ParticipationResult;
import com.errorlike.vote.entities.ParticipationResultKey;
import com.errorlike.vote.entities.QuestionOption;
import com.errorlike.vote.repositories.ParticipationRepository;
import com.errorlike.vote.repositories.ParticipationResultRepository;
import com.errorlike.vote.repositories.QuestionOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipationResultService {
    private final ParticipationResultRepository participationResultRepository;
    private final QuestionOptionRepository questionOptionRepository;

    private final ParticipationRepository participationResults;

    @Transactional
    public List<ParticipationResult> createAll(long participationId, List<Long> questionOptionIds) {


        List<QuestionOption> questionOptions = questionOptionRepository.findByIdIn(questionOptionIds);
        Optional<Participation> participation = participationResults.findById(participationId);

        List<ParticipationResult> participationResultList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now().withNano(0);
        questionOptions.forEach(questionOption -> {
            ParticipationResult participationResult = ParticipationResult.builder()
                    .participationResultKey(ParticipationResultKey
                            .builder()
                            .participationId(participationId)
                            .questionOptionId(questionOption.getId())
                            .build())
                    .participation(participation.orElseThrow())
                    .questionOption(questionOption)
                    .createTime(now)
                    .build();
            participationResultList.add(participationResult);
        });

        return participationResultRepository.saveAll(participationResultList);
    }

    public List<ParticipationResult> getResultByParticipationId(long participationId) {
        return participationResultRepository.findByParticipationId(participationId);
    }

}
