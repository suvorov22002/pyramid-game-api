package com.pyramid.game.domain.salle.dto;

import com.pyramid.game.domain.salle.model.enums.SalleStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Suvorov Vassilievitch
 * Date: 21/04/2024
 * Time: 11:14
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
public class SalleResponse {

    private Long id;
    private String codeSalle;
    private String designation;
    private String localisation;
    private String partnerCode;
    private SalleStatus status;

}
