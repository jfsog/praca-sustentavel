package com.pracasustentavel.praca_sustentavel.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pracasustentavel.praca_sustentavel.api.entity.SurveyResponse;
import com.pracasustentavel.praca_sustentavel.api.service.SurveyService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @PostMapping("/responses")
    public ResponseEntity<?> saveResponse(@RequestBody SurveyResponse response) {
        try {
            SurveyResponse savedResponse = surveyService.saveResponse(response);
            return ResponseEntity.ok(savedResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar resposta: " + e.getMessage());
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStats() {
        return ResponseEntity.ok(Map.of("totalResponses", surveyService.getTotalResponses(), "participants", surveyService.getParticipantsCount()));
    }
}