package com.pyramid.game.domain.withdraw.model;

import com.pyramid.game.core.utils.BaseEntity;
import com.pyramid.game.core.validation.MandatoryField;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

/**
 * Created by Suvorov Vassilievitch
 * Date: 11/06/2024
 * Time: 08:46
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
@FieldNameConstants
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"designation", "partner_id"}))
@Entity(name = "PYRAM_WITHDRAW")
public class Versement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MandatoryField
    private String login;

    @MandatoryField
    private String codePartner;

    @MandatoryField
    String salle;

    @MandatoryField
    private Long numeroTicket;

    @MandatoryField
    private String codeGame;

    @MandatoryField
    private Double montantVers;

    private Integer archive = 0;
}
