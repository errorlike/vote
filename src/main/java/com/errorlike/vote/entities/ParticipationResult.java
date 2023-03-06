package com.errorlike.vote.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder
public class ParticipationResult {
    @EmbeddedId
    private ParticipationResultKey participationResultKey;
    @MapsId("participationId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "participation_id")
    private Participation participation;
    @MapsId("questionOptionId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "question_option_id")
    private QuestionOption questionOption;
    private LocalDateTime createTime;

}
