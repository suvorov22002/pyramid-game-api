package com.pyramid.game.domain.bet.dto;

import com.pyramid.game.core.validation.MandatoryField;

import java.time.LocalDateTime;

public record BetRequest(
        @MandatoryField
        String codeGame,
        @MandatoryField
        Double montantMise,
        @MandatoryField
        String codePartner,
        Long barcode,
        @MandatoryField
        String cashierLogin,
        @MandatoryField
        Integer numeroTirage,
        Integer codeBonus,
        Integer numeroTicket,
        LocalDateTime heureMise,
        Double montantGain,
        Integer coefficient,
        Integer multipleRound
) {
}
