package com.errorlike.vote.dtos;

import java.time.LocalDateTime;


public interface ParticipationWithFormId {

    Long getId();

    LocalDateTime getParticipationTime();

    Long getFormId();

}
