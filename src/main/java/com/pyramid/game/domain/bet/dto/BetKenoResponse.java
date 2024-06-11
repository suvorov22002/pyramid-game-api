package com.pyramid.game.domain.bet.dto;

import com.pyramid.game.domain.slip.dto.SlipKenoResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 10/05/2024
 * Time: 21:55
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
public class BetKenoResponse {
    private Long id;
    private String codeGame;
    private Long barcode;
    private String codePartner;
    private String salle;
    private String cashierLogin;
    private Long numeroTirage;
    private String status;
    private Long codeBonus;
    private Integer numeroTicket;
    private Double montantMise;
    private Double montantGainMax;
    private Double montantGainMin;
    //private Double montantGain;
    //private Integer archive = 0;
    private Integer coefficient;
    private Integer multipleRound;
    private Double odds;
    private String codePari;
    private String selection;
    private LocalDateTime createdAt;
    private List<SlipKenoResponse> slips;
}
