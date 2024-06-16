package com.pyramid.game.domain.bet.model;

import com.pyramid.game.core.utils.BaseEntity;
import com.pyramid.game.core.validation.MandatoryField;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

/**
 * Created by Suvorov Vassilievitch
 * Date: 11/06/2024
 * Time: 11:16
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
@FieldNameConstants
@Entity(name = "PYRAM_BET")
public class Bet extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MandatoryField
    private String codeGame;

    @MandatoryField
    private String barcode;

    @MandatoryField
    private String codePartner;

    private Integer numeroTicket;

}
