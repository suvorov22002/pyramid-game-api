package com.pyramid.game.domain.bet.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pyramid.game.core.utils.BaseEntity;
import com.pyramid.game.core.validation.MandatoryField;
import com.pyramid.game.domain.bet.model.enums.BetStatus;
import com.pyramid.game.domain.slip.model.SlipKeno;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Suvorov Vassilievitch
 * Date: 10/05/2024
 * Time: 21:06
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
@FieldNameConstants
@Entity(name = "PYRAM_BETKENO")
public class BetKeno extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MandatoryField
    private String codeGame;

    @MandatoryField
    private String barcode;

    @MandatoryField
    private Double montantMise;

    @MandatoryField
    private String codePartner;

    @MandatoryField
    private String cashierLogin;

    @MandatoryField
    private String salle;

    @MandatoryField
    private Long numeroTirage;

    @MandatoryField
    private String codePari;

    @MandatoryField
    private String selection;

    private Double odds;

    @JsonManagedReference
    @OneToMany(mappedBy = "betKeno", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<SlipKeno> slips = new ArrayList<>();

    private Integer multipleRound = 1; // Ticket misé sur plusieurs tours
    private Integer coefficient = 0; // valable pour les jeux à coefficient (0: non, 1: oui)

    @Enumerated(EnumType.STRING)
    private BetStatus status = BetStatus.ATTENTE;
    private Long codeBonus;
    private Integer numeroTicket;
    private Double montantGain = 0d; // possible montant gain
    private Integer archive = 0;
    @Transient
    private Double balance;

}
