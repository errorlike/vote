package com.errorlike.vote.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ParticipationResultKey implements Serializable {

    private Long participationId;
    private Long questionOptionId;

}