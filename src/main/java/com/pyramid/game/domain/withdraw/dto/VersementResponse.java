package com.pyramid.game.domain.withdraw.dto;

import com.pyramid.game.core.validation.MandatoryField;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Suvorov Vassilievitch
 * Date: 11/06/2024
 * Time: 09:00
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
public class VersementResponse {

    private Long id;
    private String login;
    private String codePartner;
    private String salle;
    private Long numeroTicket;
    private String codeGame;
    private Double montantVers;
    private Integer archive = 0;
}
