package com.pyramid.game.domain.evenement.dto;

import com.pyramid.game.core.validation.MandatoryField;
import com.pyramid.game.domain.evenement.model.enums.EventStatus;
import com.pyramid.game.domain.jeu.model.Game;
import com.pyramid.game.domain.salle.model.Salle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by Suvorov Vassilievitch
 * Date: 10/05/2024
 * Time: 11:43
 * Project Name: pyramid-game-api
 */

@Getter
@Setter
public class EvenementResponse {

    private Long id;
    private String game;
    private String salle;
    private LocalDateTime heureTirage;
    private String tirage;
    private Long numeroTirage;
    private Long codeBonus;
    private Double montantBonus;
    private Integer multiplicateur;
    private String status;
}
