package com.errorlike.vote.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ParticipationResult that = (ParticipationResult) o;
        return getParticipationResultKey() != null && Objects.equals(getParticipationResultKey(), that.getParticipationResultKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(participationResultKey);
    }
}
