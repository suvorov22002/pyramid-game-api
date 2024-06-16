package com.pyramid.game.domain.bet.dto;

import com.pyramid.game.core.validation.MandatoryField;

import java.time.LocalDateTime;

public record BetRequest(
        @MandatoryField
        String codeGame,
        @MandatoryField
        String codePartner,
        @MandatoryField
        String barcode,
        @MandatoryField
        Integer numeroTirage,
        Integer numeroTicket
) {
}
