package com.pyramid.game.domain.partner.model;

import com.pyramid.game.core.utils.BaseEntity;
import com.pyramid.game.core.validation.MandatoryField;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/04/2024
 * Time: 01:51
 * Project Name: pyramid-game-api
 */

@Getter
@Setter
@FieldNameConstants
@Entity(name = "PYRAM_PARAMS")
public class Parameters extends BaseEntity {

    @Id
    @Column(name = "partner_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @Min(100)
    @Max(300)
    private Double miseMin = 200.0;

    @Min(10000)
    private Double miseMax = 10_000.0;

    private Double percent = 0.92;

    private Double bonusRate = 0.05;

    private Double bonusMax = 5_000.0;

}
