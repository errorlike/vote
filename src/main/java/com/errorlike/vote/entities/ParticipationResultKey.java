package com.errorlike.vote.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class ParticipationResultKey implements Serializable {

    private Long participationId;
    private Long questionOptionId;

}