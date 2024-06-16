package com.pyramid.game.domain.salle.model;

import com.pyramid.game.core.utils.BaseEntity;
import com.pyramid.game.core.validation.MandatoryField;
import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.salle.model.enums.SalleStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/04/2024
 * Time: 00:12
 * Project Name: pyramid-game-api
 */

@Getter
@Setter
@FieldNameConstants
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"designation", "partner_id"}))
@Entity(name = "PYRAM_SALLE")
public class Salle extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MandatoryField
    private String codeSalle;

    @MandatoryField
    private String designation;

    private String localisation;

    @Enumerated(EnumType.STRING)
    private SalleStatus status = SalleStatus.ACTIVE;

    @MandatoryField
    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;
}
