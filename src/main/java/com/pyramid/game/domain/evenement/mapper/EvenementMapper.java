package com.pyramid.game.domain.evenement.mapper;

import com.pyramid.game.domain.evenement.dto.EvenementRequest;
import com.pyramid.game.domain.evenement.dto.EvenementResponse;
import com.pyramid.game.domain.evenement.model.Evenement;
import com.pyramid.game.domain.jeu.model.Game;
import com.pyramid.game.domain.jeu.repository.GameRepository;
import com.pyramid.game.domain.salle.dto.SalleRequest;
import com.pyramid.game.domain.salle.dto.SalleResponse;
import com.pyramid.game.domain.salle.model.Salle;
import com.pyramid.game.domain.salle.repository.SalleRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Created by Suvorov Vassilievitch
 * Date: 10/05/2024
 * Time: 11:14
 * Project Name: pyramid-game-api
 */
@Component
@Mapper(componentModel = "spring")
public abstract class EvenementMapper {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    SalleRepository salleRepository;

    public abstract List<EvenementResponse> toDtoList(List<Evenement> evenements);
    @Mapping(target = "salle", expression = "java(resolveSalleCode(evenement.getSalle().getId()))")
    @Mapping(target = "game", expression = "java(resolveGameCode(evenement.getGame().getId()))")
    public abstract EvenementResponse toDto(Evenement evenement);

    @Mapping(target = "salle", expression = "java(resolveSalle(request))")
    @Mapping(target = "game", expression = "java(resolveGame(request))")
    public abstract Evenement toEntity(EvenementRequest request);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    public abstract Evenement update(Evenement source, @MappingTarget Evenement target);

    protected String resolveSalleCode(Long id){
        return Objects.requireNonNull(salleRepository.findById(id).orElse(null)).getCodeSalle();
    }

    protected String resolveGameCode(Long id) {
        return Objects.requireNonNull(gameRepository.findById(id).orElse(null)).getCode();
    }

    protected Salle resolveSalle(EvenementRequest request) {
        return salleRepository.findSalleByCodeSalle(request.salle()).orElse(null);
    }

    protected Game resolveGame(EvenementRequest request) {
        return gameRepository.findByCode(request.game()).orElse(null);
    }
}
