package com.pracasustentavel.praca_sustentavel.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pracasustentavel.praca_sustentavel.api.entity.SurveyResponse;
import com.pracasustentavel.praca_sustentavel.api.entity.WordCount;
import com.pracasustentavel.praca_sustentavel.api.repository.SurveyResponseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WordCloudService {

    private final SurveyResponseRepository repository;

    public List<WordCount> generateWordCloudForImprovement() {
        return generateWordCloud("improvement");
    }

    public List<WordCount> generateWordCloudForActivities() {
        return generateWordCloud("activities");
    }

    public List<WordCount> generateWordCloudForSustainability() {
        return generateWordCloud("sustainability");
    }

    private List<WordCount> generateWordCloud(String field) {
        List<String> allTexts = new ArrayList<>();
        List<SurveyResponse> allResponses = repository.findAll();

        for (SurveyResponse response : allResponses) {
            String text = switch (field) {
                case "improvement" -> response.getImprovement();
                case "activities" -> response.getActivities();
                case "sustainability" -> response.getSustainability();
                default -> "";
            };

            if (text != null && ! text.trim().isEmpty()) {
                allTexts.add(text.toLowerCase());
            }
        }

        return processTexts(allTexts);
    }

    private List<WordCount> processTexts(List<String> texts) {
        Map<String, Integer> wordCount = new HashMap<>();

        for (String text : texts) {
            // Remove pontuação e divide em palavras
            String[] words = text.replaceAll("[^a-záàâãéèêíïóôõöúçñ\\s]", "").split("\\s+");

            for (String word : words) {
                if (word.length() > 3) { // Ignora palavras muito curtas
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                }
            }
        }

        // Converte para lista ordenada
        return wordCount.entrySet()
                .stream()
                .map(entry -> WordCount.builder().count(entry.getValue()).word(entry.getKey()).build())
                .sorted((a, b) -> Integer.compare(b.getCount(), a.getCount()))
                .limit(30)
                .collect(Collectors.toList());
    }
}