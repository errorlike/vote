package com.errorlike.vote.utils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum QuestionType {
    RADIO(1),
    MULTI(2);
    public final int questionValue;
}
