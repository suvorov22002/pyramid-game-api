package com.pyramid.game.domain.withdraw.mapper;

import com.pyramid.game.domain.withdraw.dto.VersementRequest;
import com.pyramid.game.domain.withdraw.dto.VersementResponse;
import com.pyramid.game.domain.withdraw.model.Versement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 11/06/2024
 * Time: 10:26
 * Project Name: pyramid-game-api
 */
@Component
@Mapper(componentModel = "spring")
public abstract class VersementMapper {

    public abstract List<VersementResponse> toDtoList(List<Versement> versements);

    public abstract VersementResponse toDto(Versement versement);
    public abstract Versement toEntity(VersementRequest request);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    public abstract Versement update(Versement source, @MappingTarget Versement target);

}
