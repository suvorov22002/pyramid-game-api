package com.pyramid.game.domain.evenement.dto;

import com.pyramid.game.core.validation.MandatoryField;

import java.time.LocalDateTime;

public record EvenementRequest(
        String game,
        String salle,
        LocalDateTime heureTirage,
        String tirage,
        @MandatoryField
        Long numeroTirage,
        Long codeBonus,
        Double montantBonus,
        Integer multiplicateur,
        String status
) {
}
