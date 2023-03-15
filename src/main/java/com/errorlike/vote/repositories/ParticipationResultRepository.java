package com.errorlike.vote.repositories;

import com.errorlike.vote.dtos.StatisticsForBarChartProjection;
import com.errorlike.vote.entities.ParticipationResult;
import com.errorlike.vote.entities.ParticipationResultKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParticipationResultRepository extends JpaRepository<ParticipationResult, ParticipationResultKey> {
    List<ParticipationResult> findByParticipationId(Long id);


    @Query("""
            SELECT
                COUNT(*) as selectedNumber,
                pr.questionOption.name as questionOptionName,
                pr.questionOption.question.name as questionName
            FROM
                ParticipationResult pr
            JOIN
                pr.questionOption q
            JOIN
                pr.questionOption.question q
            WHERE q.form.id=:formId
            GROUP BY pr.questionOption.id
            """)
    List<StatisticsForBarChartProjection> getStatisticsForBarChartData(Long formId);

}