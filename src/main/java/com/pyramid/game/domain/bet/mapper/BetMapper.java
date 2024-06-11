package com.pyramid.game.domain.bet.mapper;

import com.pyramid.game.domain.bet.dto.BetKenoRequest;
import com.pyramid.game.domain.bet.dto.BetKenoResponse;
import com.pyramid.game.domain.bet.dto.BetRequest;
import com.pyramid.game.domain.bet.model.BetKeno;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 10/05/2024
 * Time: 21:58
 * Project Name: pyramid-game-api
 */
//@Component
//@Mapper(componentModel = "spring")
public abstract class BetMapper {

    public abstract List<BetKenoResponse> toDtoList(List<BetKeno> bets);
    public abstract BetKenoResponse toDto(BetKeno bet);

    @Mapping(target = "archive", ignore = true)
    @Mapping(target = "status", ignore = true)
    public abstract BetKeno toEntity(BetKenoRequest request);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    public abstract BetKeno update(BetKeno source, @MappingTarget BetKeno target);
}
