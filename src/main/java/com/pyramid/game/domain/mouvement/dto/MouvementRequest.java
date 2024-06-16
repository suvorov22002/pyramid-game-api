package com.pyramid.game.domain.mouvement.dto;

import com.pyramid.game.core.validation.MandatoryField;

import java.time.LocalDateTime;

public record MouvementRequest(
        Double credit,
        Double debit,
        String operation,
        Double balance,
        @MandatoryField
        String login,
        @MandatoryField
        String partner
) {
}
