package com.pyramid.game.domain.users.mapper;

import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.partner.repository.PartnerRepository;
import com.pyramid.game.domain.users.dto.AppUserRequest;
import com.pyramid.game.domain.users.dto.AppUserResponse;
import com.pyramid.game.domain.users.model.AppUser;
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
 * Time: 10:26
 * Project Name: pyramid-game-api
 */
@Component
@Mapper(componentModel = "spring")
public abstract class AppUserMapper {

    @Autowired
    private PartnerRepository partnerRepository;
    public abstract List<AppUserResponse> toDtoList(List<AppUser> appUsers);

    @Mapping(target = "partnerCode", expression = "java(resolvePartnerCode(appUser.getPartner().getId()))")
    public abstract AppUserResponse toDto(AppUser appUser);

    @Mapping(target = "partner", expression = "java(resolvePartner(request))")
    public abstract AppUser toEntity(AppUserRequest request);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    public abstract AppUser update(AppUser source, @MappingTarget AppUser target);


    protected String resolvePartnerCode(Long id) {
        return Objects.requireNonNull(partnerRepository.findById(id).orElse(null)).getDesignation();
    }

    protected Partner resolvePartner(AppUserRequest request) {
        return partnerRepository.findPartnerByCodePartner(request.partnerCode()).orElse(null);
    }
}
