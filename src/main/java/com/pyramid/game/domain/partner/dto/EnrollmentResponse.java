package com.pyramid.game.domain.partner.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/04/2024
 * Time: 17:17
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
public class EnrollmentResponse {

    private Long id;
    private Long partner;
    private String game;
    private LocalDateTime enrollAt;
    private LocalDateTime deregistrationAt;
    private String status;
    private Double miseMin;
    private Double miseMax;
    private Double percent;
    private Double bonusRate;
    private Double bonusMax;
    private Boolean inheritConfig;
}
