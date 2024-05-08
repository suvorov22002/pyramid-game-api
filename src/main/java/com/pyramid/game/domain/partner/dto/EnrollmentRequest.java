package com.pyramid.game.domain.partner.dto;

import com.pyramid.game.core.validation.MandatoryField;

import java.time.LocalDateTime;

public record EnrollmentRequest(
        @MandatoryField
        String game,

        @MandatoryField
        String partner,
        LocalDateTime enrollAt,
        LocalDateTime deregistrationAt,
        String status,
        Double miseMin,
        Double miseMax,
        Double percent,
        Double bonusRate,
        Double bonusMax,
        Boolean inheritConfig
) {
}
