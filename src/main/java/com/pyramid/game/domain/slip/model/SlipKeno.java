package com.pyramid.game.domain.slip.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pyramid.game.core.utils.BaseEntity;
import com.pyramid.game.core.validation.MandatoryField;
import com.pyramid.game.domain.bet.model.BetKeno;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

/**
 * Created by Suvorov Vassilievitch
 * Date: 10/05/2024
 * Time: 22:28
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Entity(name = "PYRAM_SLIPKENO")
public class SlipKeno extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String selection;
    private Double montantSelection;
    private Double montantWin;
    private String codePari;

    @MandatoryField
    private Long numeroTirage;

    @MandatoryField
    private Double odds;

    @MandatoryField
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "betkeno_id")
    private BetKeno betKeno;
}
