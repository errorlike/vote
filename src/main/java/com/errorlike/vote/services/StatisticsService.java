package com.errorlike.vote.services;

import com.errorlike.vote.dtos.StatisticsForBarChartDTO;
import com.errorlike.vote.dtos.StatisticsForBarChartProjection;
import com.errorlike.vote.repositories.ParticipationRepository;
import com.errorlike.vote.repositories.ParticipationResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    //fixme maybe have > 100 percentage sum
    private final ParticipationResultRepository participationResultRepository;

    private final ParticipationRepository participationRepository;

    public List<StatisticsForBarChartDTO> getBarDataByFormId(long formId) {
        List<StatisticsForBarChartProjection> barChartProjections =
                participationResultRepository.getStatisticsForBarChartData(formId);
        int participationNumber = participationRepository.countByForm_Id(formId);

        List<StatisticsForBarChartDTO> statisticsForBarChartDTOS = new ArrayList<>();
        barChartProjections.forEach(barChartProjection -> {
            StatisticsForBarChartDTO statisticsForBarChartDTO = StatisticsForBarChartDTO
                    .builder()
                    .questionName(barChartProjection.getQuestionName())
                    .questionOptionName(barChartProjection.getQuestionOptionName())
                    .percentage(Math.round(barChartProjection.getSelectedNumber() * 100.0f / participationNumber))
                    .build();
            statisticsForBarChartDTOS.add(statisticsForBarChartDTO);
        });

        return statisticsForBarChartDTOS;
    }
}
