package com.pyramid.game.domain.partner.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/04/2024
 * Time: 09:18
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
public class PartnerResponse {

    private Long id;
    private String codePartner;
    private String designation;
    private String localisation;
    private String status;
    private List<String> games;
    private Long parameters;

}
