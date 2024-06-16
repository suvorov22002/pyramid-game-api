package com.pyramid.game.domain.bet.mapper;

import com.pyramid.game.domain.bet.dto.BetKenoRequest;
import com.pyramid.game.domain.bet.dto.BetKenoResponse;
import com.pyramid.game.domain.bet.dto.BetRequest;
import com.pyramid.game.domain.bet.model.BetKeno;
import com.pyramid.game.domain.slip.dto.SlipKenoResponse;
import com.pyramid.game.domain.slip.model.SlipKeno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 11/05/2024
 * Time: 09:15
 * Project Name: pyramid-game-api
 */
@Component
@Mapper(componentModel = "spring")
public abstract class BetKenoMapper {

    public abstract List<BetKenoResponse> toDtoList(List<BetKeno> bets);

    @Mapping(target = "slips", expression = "java(resolveSlip(bet))")
    @Mapping(target = "montantGainMax", expression = "java(resolveGainMax(bet))")
    @Mapping(target = "montantGainMin", expression = "java(resolveGainMin(bet))")
    public abstract BetKenoResponse toDto(BetKeno bet);

    @Mapping(target = "slips", ignore = true)
    @Mapping(target = "archive", ignore = true)
    @Mapping(target = "status", ignore = true)
    public abstract BetKeno toEntity(BetKenoRequest request);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    public abstract BetKeno update(BetKeno source, @MappingTarget BetKeno target);

    protected List<SlipKenoResponse> resolveSlip(BetKeno bet) {
        List<SlipKenoResponse> slipKenoResponses = new ArrayList<>();
        for(SlipKeno s : bet.getSlips()) {
            SlipKenoResponse slipr = SlipKenoResponse.builder()
                    .montantSelection(s.getMontantSelection())
                    .montantWin(s.getMontantWin())
                    .codePari(s.getCodePari())
                    .selection(s.getSelection())
                    .numeroTirage(s.getNumeroTirage())
                    .odds(s.getOdds())
                    .build();

            slipKenoResponses.add(slipr);
        }

        return slipKenoResponses;
    }

    protected Double resolveGainMax(BetKeno bet) {
        return bet.getOdds() * bet.getMontantMise();
    }

    protected Double resolveGainMin(BetKeno bet) {
        double gainMin = bet.getOdds() * (bet.getMontantMise() / bet.getMultipleRound());
        gainMin = (double)((int)(gainMin*100))/100;

        return gainMin;
    }

}
