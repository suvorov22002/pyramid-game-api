package com.pyramid.game.domain.partner.dto;
public record ParameterRequest(
         Double miseMin,
         Double miseMax,
         Double percent,
         Double bonusRate,
         Double bonusMax
) {
}
