package com.pracasustentavel.praca_sustentavel.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pracasustentavel.praca_sustentavel.api.entity.SurveyResponse;
import com.pracasustentavel.praca_sustentavel.api.repository.SurveyResponseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyResponseRepository repository;

    public SurveyResponse saveResponse(SurveyResponse response) {
        return repository.save(response);
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