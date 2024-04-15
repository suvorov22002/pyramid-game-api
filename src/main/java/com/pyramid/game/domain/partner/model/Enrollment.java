package com.pyramid.game.domain.partner.model;

import com.pyramid.game.core.utils.BaseEntity;
import com.pyramid.game.domain.jeu.model.Game;
import com.pyramid.game.domain.partner.model.enums.EnrollStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/04/2024
 * Time: 01:26
 * Project Name: pyramid-game-api
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Entity(name = "PYRAM_ENROLL")
public class Enrollment extends BaseEntity {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    LocalDateTime enrollAt;

    LocalDateTime deregistrationAt;

    @Enumerated(EnumType.STRING)
    EnrollStatus status = EnrollStatus.ENROLLED;

    private Double miseMin;

    private Double miseMax;

    private Double percent; // part de distribution du game

    private Double bonusRate; // pourcentage du bonus sur la mise

    private Double bonusMax; // Montant max pour lequel le b
}
