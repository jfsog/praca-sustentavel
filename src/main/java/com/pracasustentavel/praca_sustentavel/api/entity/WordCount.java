package com.pracasustentavel.praca_sustentavel.api.entity;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WordCount {
    private String word;
    private int count;

    public static WordCount of(Map.Entry<String, Integer> mapEntry) {
        return WordCount.builder()
                .word(mapEntry.getKey())
                .count(mapEntry.getValue())
                .build();
    }
}
