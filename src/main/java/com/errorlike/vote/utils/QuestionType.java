package com.errorlike.vote.utils;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter(onMethod = @__(@JsonValue))
public enum QuestionType {
    RADIO(1),
    MULTI(2);
    public final Integer questionValue;
}
