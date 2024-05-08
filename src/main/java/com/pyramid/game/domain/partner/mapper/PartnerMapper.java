package com.pyramid.game.domain.partner.mapper;

import com.pyramid.game.domain.partner.dto.PartnerRequest;
import com.pyramid.game.domain.partner.dto.PartnerResponse;
import com.pyramid.game.domain.partner.model.Parameters;
import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.partner.repository.ParametersRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/04/2024
 * Time: 13:27
 * Project Name: pyramid-game-api
 */
@Component
@Mapper(componentModel = "spring")
public abstract class PartnerMapper {

    @Autowired
    private ParametersRepository parametersRepository;
    public abstract List<PartnerResponse> toDtoList(List<Partner> partners);

    @Mapping(target = "games", expression = "java(extraGamesCode(partner))")
    //@Mapping(target = "parameters", expression = "java(partner.getParameters().getId())")
    public abstract PartnerResponse toDto(Partner partner);

    @Mapping(target = "enrollments", ignore = true)
    public abstract Partner toEntity(PartnerRequest request);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    public abstract Partner update(Partner source, @MappingTarget Partner target);

    protected List<String> extraGamesCode(Partner partner) {
        return partner.getEnrollments().stream().map(e -> e.getGame().getCode()).collect(Collectors.toList());
    }

    protected List<String> extraGames(PartnerRequest request) {

        return null;
    }

    protected Parameters resolveParam(Long id){
        return parametersRepository.findById(id).orElse(null);
    }

    //protected Set<Enrollment> resolveEnrollment(PartnerRequest request) {
    //    request.enrollments().stream().map()
    //}
}
