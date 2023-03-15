package com.errorlike.vote.dtos;

import java.time.LocalDateTime;


public interface ParticipationWithFormIdProjection {

    Long getId();

    LocalDateTime getParticipationTime();

    Long getFormId();

}
