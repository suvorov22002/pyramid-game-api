package com.pyramid.game.domain.bet.model;

import com.pyramid.game.core.validation.MandatoryField;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by Suvorov Vassilievitch
 * Date: 11/06/2024
 * Time: 11:16
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
@MappedSuperclass
public abstract class Bet {

    @MandatoryField
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    @MandatoryField
    private String codeGame;

    @MandatoryField
    private Long barcode;

    private Integer numeroTicket;

}
