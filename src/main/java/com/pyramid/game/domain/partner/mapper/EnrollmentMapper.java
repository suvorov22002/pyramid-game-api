package com.pyramid.game.domain.partner.mapper;

import com.pyramid.game.domain.jeu.model.Game;
import com.pyramid.game.domain.jeu.repository.GameRepository;
import com.pyramid.game.domain.partner.dto.EnrollmentRequest;
import com.pyramid.game.domain.partner.dto.EnrollmentResponse;
import com.pyramid.game.domain.partner.dto.PartnerRequest;
import com.pyramid.game.domain.partner.dto.PartnerResponse;
import com.pyramid.game.domain.partner.model.Enrollment;
import com.pyramid.game.domain.partner.model.Partner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/04/2024
 * Time: 17:15
 * Project Name: pyramid-game-api
 */
@Component
@Mapper(componentModel = "spring")
public abstract class EnrollmentMapper {

    @Autowired
    private GameRepository gameRepository;

    public abstract List<EnrollmentResponse> toDtoList(List<Enrollment> enrollments);

    @Mapping(target = "partner", expression = "java(enrollment.getPartner().getId())")
    @Mapping(target = "game", expression = "java(enrollment.getGame().getCode())")
    public abstract EnrollmentResponse toDto(Enrollment enrollment);

    @Mapping(target = "game", expression = "java(resolveGame(request))")
    public abstract Enrollment toEntity(EnrollmentRequest request);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    public abstract Enrollment update(Enrollment source, @MappingTarget Enrollment target);

    protected Game resolveGame(EnrollmentRequest request) {
        return gameRepository.findByCode(request.game());
    }
}
