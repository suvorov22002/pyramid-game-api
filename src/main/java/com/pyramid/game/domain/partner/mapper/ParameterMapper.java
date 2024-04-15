package com.pyramid.game.domain.partner.mapper;

import com.pyramid.game.domain.partner.dto.EnrollmentRequest;
import com.pyramid.game.domain.partner.dto.EnrollmentResponse;
import com.pyramid.game.domain.partner.dto.ParameterRequest;
import com.pyramid.game.domain.partner.dto.ParameterResponse;
import com.pyramid.game.domain.partner.model.Enrollment;
import com.pyramid.game.domain.partner.model.Parameters;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/04/2024
 * Time: 17:25
 * Project Name: pyramid-game-api
 */
@Component
@Mapper(componentModel = "spring")
public abstract class ParameterMapper {

    public abstract List<ParameterResponse> toDtoList(List<Parameters> parameters);

    @Mapping(target = "partner", expression = "java(parameter.getPartner().getId())")
    public abstract ParameterResponse toDto(Parameters parameter);

    public abstract Parameters toEntity(ParameterRequest request);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    public abstract Parameters update(Parameters source, @MappingTarget Parameters target);

}
