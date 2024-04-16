package com.pyramid.game.domain.jeu.dto;

import com.pyramid.game.core.validation.MandatoryField;

public record GameRequest(
        @MandatoryField
        String code,
        String designation,
        String description,
        String status
) {
}
