package com.pyramid.game.domain.withdraw.dto;

import com.pyramid.game.core.validation.MandatoryField;

public record VersementRequest(
        @MandatoryField
         String login,
        @MandatoryField
         String codePartner,
        @MandatoryField
        String salle,
        @MandatoryField
        Long numeroTicket,
        @MandatoryField
        String codeGame,
        @MandatoryField
        Double montantVers
) {
}
