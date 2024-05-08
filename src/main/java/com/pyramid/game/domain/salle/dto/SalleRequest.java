package com.pyramid.game.domain.salle.dto;

import com.pyramid.game.core.validation.MandatoryField;

public record SalleRequest(
        String designation,
        String localisation,
        String status,
        @MandatoryField
        String partnerCode
) {
}
