package com.pyramid.game.domain.jeu.mapper;

import com.pyramid.game.domain.jeu.dto.GameRequest;
import com.pyramid.game.domain.jeu.dto.GameResponse;
import com.pyramid.game.domain.jeu.model.Game;
import com.pyramid.game.domain.partner.dto.ParameterRequest;
import com.pyramid.game.domain.partner.dto.ParameterResponse;
import com.pyramid.game.domain.partner.model.Parameters;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 16/04/2024
 * Time: 20:29
 * Project Name: pyramid-game-api
 */
@Component
@Mapper(componentModel = "spring")
public abstract class GameMapper {

    public abstract List<GameResponse> toDtoList(List<Game> games);

    public abstract GameResponse toDto(Game game);
    public abstract Game toEntity(GameRequest request);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    public abstract Game update(Game source, @MappingTarget Game target);

}
