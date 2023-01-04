package com.errorlike.vote.entities;

import javax.persistence.CascadeType;
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
    @JoinColumn(name = "participation_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Participation participation;
    @MapsId("question_option_id")
    @JoinColumn(name = "qustion_option_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private QuestionOption questionOption;

}
