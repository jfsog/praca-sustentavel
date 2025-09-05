package com.pracasustentavel.praca_sustentavel.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import com.pracasustentavel.praca_sustentavel.api.entity.SurveyResponse;
import com.pracasustentavel.praca_sustentavel.api.entity.SurveyResponseDTO;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SurveyResponseMapper {

    SurveyResponse toEntity(SurveyResponseDTO dto);
}
