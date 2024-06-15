package com.pyramid.game.domain.mouvement.mapper;

import com.pyramid.game.core.utils.Constants;
import com.pyramid.game.domain.mouvement.dto.MouvementDto;
import com.pyramid.game.domain.mouvement.dto.MouvementRequest;
import com.pyramid.game.domain.mouvement.model.Mouvement;
import com.pyramid.game.domain.partner.dto.ParameterRequest;
import com.pyramid.game.domain.partner.model.Parameters;
import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.partner.repository.PartnerRepository;
import com.pyramid.game.domain.shift.dto.ShiftDto;
import com.pyramid.game.domain.shift.model.Shift;
import com.pyramid.game.domain.users.model.AppUser;
import com.pyramid.game.domain.users.repository.AppUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 14/06/2024
 * Time: 20:03
 * Project Name: pyramid-game-api
 */
@Component
@Mapper(componentModel = "spring")
public abstract class MouvementMapper {

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    AppUserRepository appUserRepo;

    public abstract List<MouvementDto> toDtoList(List<Mouvement> mouvements);

    @Mapping(target = "partner", expression = "java(resolvePartner(mouvement))")
    @Mapping(target = "login", expression = "java(mouvement.getAppUser().getLogin())")
    public abstract MouvementDto toDto(Mouvement mouvement);
    @Mapping(target = "appUser", expression = "java(resolveUser(request))")
    public abstract Mouvement toEntity(MouvementRequest request);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    public abstract Mouvement update(Mouvement source, @MappingTarget Mouvement target);

    protected String resolvePartner(Mouvement request) {
        return request.getAppUser().getPartner().getCodePartner();
    }

    protected AppUser resolveUser(MouvementRequest request) {

        var login = request.login();
        var codePartner = request.partner();
        Partner partner = partnerRepository.findPartnerByCodePartner(codePartner).orElse(
                null
        );

        return appUserRepo.findAppUserByPartnerAndLogin(partner, login).orElseThrow(
                () -> new EntityNotFoundException(Constants.USER_NOT_FOUND)
        );
    }


}
