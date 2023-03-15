package com.errorlike.vote.repositories;

import com.errorlike.vote.dtos.ParticipationWithFormIdProjection;
import com.errorlike.vote.entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

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
    List<ParticipationWithFormIdProjection> findParticipationWithFormIdByUserId(@Param("userId") long userId);

    int countByForm_Id(Long id);


}