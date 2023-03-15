package com.errorlike.vote.dtos;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class StatisticsForBarChartDTO {
    String questionName;
    String questionOptionName;
    int percentage;

}
