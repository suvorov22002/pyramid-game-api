package com.pyramid.game.domain.slip.dto;

import com.pyramid.game.core.validation.MandatoryField;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Suvorov Vassilievitch
 * Date: 12/05/2024
 * Time: 17:57
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
@Builder
public class SlipKenoResponse {

    private String selection;
    private Double montantSelection;
    private Double montantWin;
    private String codePari;
    private Long numeroTirage;
    private Double odds;
}
