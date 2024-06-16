package com.pyramid.game.domain.shift.mapper;

import com.pyramid.game.domain.shift.dto.ShiftDto;
import com.pyramid.game.domain.shift.model.Shift;
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
 * Date: 12/06/2024
 * Time: 06:26
 * Project Name: pyramid-game-api
 */
@Component
@Mapper(componentModel = "spring")
public abstract class ShiftMapper {

    public abstract List<ShiftDto> toDtoList(List<Shift> shifts);

    @Mapping(target = "totalBalance", expression = "java(resolveTotalBalance(shift))")
    public abstract ShiftDto toDto(Shift shift);
    public abstract Shift toEntity(ShiftDto request);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    public abstract Shift update(Shift source, @MappingTarget Shift target);

    protected Double resolveTotalBalance(Shift shift) {
        return shift.getTotalPayin() - shift.getTotalPayout();
    }
}
