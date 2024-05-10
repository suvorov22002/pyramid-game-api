package com.pyramid.game.domain.partner.dto;

import com.pyramid.game.core.validation.MandatoryField;

public record ParameterRequest(
         @MandatoryField
         String partner,
         Double miseMin,
         Double miseMax,
         Double percent,
         Double bonusRate,
         Double bonusMax
) {
}
