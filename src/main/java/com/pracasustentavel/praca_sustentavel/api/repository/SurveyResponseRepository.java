package com.pracasustentavel.praca_sustentavel.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pracasustentavel.praca_sustentavel.api.entity.SurveyResponse;

@Repository
public interface SurveyResponseRepository extends JpaRepository<SurveyResponse, Long> {
    @Query("SELECT DISTINCT s.neighborhood FROM SurveyResponse s")
    List<String> findDistinctNeighborhoods();

    long count();
}
