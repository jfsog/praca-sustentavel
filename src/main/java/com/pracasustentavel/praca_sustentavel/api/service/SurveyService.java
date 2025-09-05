package com.pracasustentavel.praca_sustentavel.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pracasustentavel.praca_sustentavel.api.entity.SurveyResponse;
import com.pracasustentavel.praca_sustentavel.api.entity.SurveyResponseDTO;
import com.pracasustentavel.praca_sustentavel.api.mapper.SurveyResponseMapper;
import com.pracasustentavel.praca_sustentavel.api.repository.SurveyResponseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyResponseRepository repository;
    private final SurveyResponseMapper mapper;

    public SurveyResponse saveResponse(SurveyResponseDTO response) {
        return repository.save(mapper.toEntity(response));
    }

    public long getTotalResponses() {
        return repository.count();
    }

    public long getParticipantsCount() {
        return repository.findDistinctNeighborhoods().size();
    }

    public List<SurveyResponse> getAllResponses() {
        return repository.findAll();
    }
}