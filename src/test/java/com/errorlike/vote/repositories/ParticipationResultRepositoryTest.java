package com.errorlike.vote.repositories;

import com.errorlike.vote.dtos.StatisticsForBarChartProjection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest

class ParticipationResultRepositoryTest {
    @Autowired
    private ParticipationResultRepository participationResultRepository;
    @Test
    void getStatisticsForBarChartDTO() {
        List<StatisticsForBarChartProjection> statisticsForBarChartProjection = participationResultRepository.getStatisticsForBarChartData(6L);
      statisticsForBarChartProjection.forEach(
              item-> {
                  System.out.println(item.getQuestionOptionName());
                  System.out.println(item.getSelectedNumber());
              }

      );

    }
}