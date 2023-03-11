package com.errorlike.vote.repositories;

import com.errorlike.vote.dtos.ParticipationWithFormId;
import com.errorlike.vote.entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    List<ParticipationWithFormId> findByUser_Id(Long id);

    @Query("""
             select
                p.id as id,
            	p.participationTime as participationTime,
            	p.form.id as formId
            from
            	Participation p
            where
            	p.user.id = :userId
            	""")
    List<ParticipationWithFormId> findParticipationWithFormIdByUserId(@Param("userId") long userId);

}