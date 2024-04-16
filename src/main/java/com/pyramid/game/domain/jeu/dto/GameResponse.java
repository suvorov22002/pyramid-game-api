package com.pyramid.game.domain.jeu.dto;

import com.pyramid.game.domain.jeu.model.enums.GameStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Suvorov Vassilievitch
 * Date: 16/04/2024
 * Time: 20:22
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
public class GameResponse {

    private Long id;
    private String code;
    private String designation;
    private String description;
    private String status;


}
