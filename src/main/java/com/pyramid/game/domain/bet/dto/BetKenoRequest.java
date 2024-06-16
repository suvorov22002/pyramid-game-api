package com.pyramid.game.domain.bet.dto;

import com.pyramid.game.core.validation.MandatoryField;

import java.time.LocalDateTime;

public record BetKenoRequest(
        @MandatoryField
        String codeGame,
        @MandatoryField
        Double montantMise,
        @MandatoryField
        String codePartner,
        @MandatoryField
        String salle,
        //Long barcode,
        @MandatoryField
        String cashierLogin,
        @MandatoryField
        Long numeroTirage,
        //Long codeBonus,
        //Integer numeroTicket,
        LocalDateTime heureMise,
        //Double montantGain,
        Integer coefficient,
        Integer multipleRound,
        //Double odds,
        @MandatoryField
        String codePari,
        @MandatoryField
        String selection
) {
}
