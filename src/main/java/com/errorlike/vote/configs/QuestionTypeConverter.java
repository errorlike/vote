package com.errorlike.vote.configs;

import com.errorlike.vote.utils.QuestionType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class QuestionTypeConverter implements AttributeConverter<QuestionType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(QuestionType questionType) {
        if (questionType == null) {
            return null;
        }
        return questionType.getQuestionValue();
    }

    @Override
    public QuestionType convertToEntityAttribute(Integer questionValue) {
        if (questionValue == null) {
            return null;
        }

        return Stream.of(QuestionType.values())
                .filter(c -> c.getQuestionValue().equals(questionValue))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}