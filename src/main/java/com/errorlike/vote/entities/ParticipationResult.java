package com.errorlike.vote.entities;

import java.time.LocalDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)

public class ParticipationResult {
    @EmbeddedId
    private ParticipationResultKey participationResultKey;
    @MapsId("participationId")
    @ManyToOne
    @JoinColumn(name = "participation_id")
    private Participation participation;
    @MapsId("questionOptionId")
    @ManyToOne
    @JoinColumn(name = "question_option_id")
    private QuestionOption questionOption;
    LocalDateTime createTime;

}
