package com.pyramid.game.domain.partner.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/04/2024
 * Time: 17:21
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
public class ParameterResponse {
    
    private Long id;
    private Long partner;
    private Double miseMin;
    private Double miseMax;
    private Double percent;
    private Double bonusRate;
    private Double bonusMax;
}
