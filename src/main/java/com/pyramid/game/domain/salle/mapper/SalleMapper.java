package com.pyramid.game.domain.salle.mapper;

import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.partner.repository.PartnerRepository;
import com.pyramid.game.domain.salle.dto.SalleRequest;
import com.pyramid.game.domain.salle.dto.SalleResponse;
import com.pyramid.game.domain.salle.model.Salle;
import com.pyramid.game.domain.users.dto.AppUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Created by Suvorov Vassilievitch
 * Date: 21/04/2024
 * Time: 11:16
 * Project Name: pyramid-game-api
 */
@Component
@Mapper(componentModel = "spring")
public abstract class SalleMapper {

    @Autowired
    private PartnerRepository partnerRepository;

    public abstract List<SalleResponse> toDtoList(List<Salle> salles);
    @Mapping(target = "partnerCode", expression = "java(resolvePartnerCode(salle.getPartner().getId()))")
    public abstract SalleResponse toDto(Salle salle);

    @Mapping(target = "partner", expression = "java(resolvePartner(request))")
    public abstract Salle toEntity(SalleRequest request);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    public abstract Salle update(Salle source, @MappingTarget Salle target);

    protected String resolvePartnerCode(Long id) {
        return Objects.requireNonNull(partnerRepository.findById(id).orElse(null)).getDesignation();
    }

    protected Partner resolvePartner(SalleRequest request) {
        return partnerRepository.findPartnerByDesignation(request.partnerCode()).orElse(null);
    }

}
