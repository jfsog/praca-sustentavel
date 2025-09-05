package com.pracasustentavel.praca_sustentavel.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SurveyResponseDTO {

    private Integer age;

    @Max(1000)
    private String neighborhood;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @Max(1000)
    private String improvement;

    @Max(1000)
    private String activities;

    @Max(1000)
    private String sustainability;
}
