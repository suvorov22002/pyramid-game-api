package com.pyramid.game.domain.partner.dto;

import java.time.LocalDateTime;

public record EnrollmentRequest(
        String game,
        LocalDateTime enrollAt,
        LocalDateTime deregistrationAt,
        String status,
        Double miseMin,
        Double miseMax,
        Double percent,
        Double bonusRate,
        Double bonusMax
) {
}
