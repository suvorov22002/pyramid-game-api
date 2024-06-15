package com.pyramid.game.domain.mouvement.dto;

import java.time.LocalDateTime;

public record MouvementRequest(
        Double credit,
        Double debit,
        String operation,
        Double balance,
        String login,
        String partner
) {
}
