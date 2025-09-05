package com.pracasustentavel.praca_sustentavel.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pracasustentavel.praca_sustentavel.api.entity.WordCount;
import com.pracasustentavel.praca_sustentavel.api.service.WordCloudService;

@RestController
@RequestMapping("/api/wordcloud")
@CrossOrigin(origins = "*")
public class WordCloudController {

    @Autowired
    private WordCloudService wordCloudService;

    @GetMapping("/improvement")
    public List<WordCount> getImprovementWordCloud() {
        return wordCloudService.generateWordCloudForImprovement();
    }

    @GetMapping("/activities")
    public List<WordCount> getActivitiesWordCloud() {
        return wordCloudService.generateWordCloudForActivities();
    }

    @GetMapping("/sustainability")
    public List<WordCount> getSustainabilityWordCloud() {
        return wordCloudService.generateWordCloudForSustainability();
    }
}