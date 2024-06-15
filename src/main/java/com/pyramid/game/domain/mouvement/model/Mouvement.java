package com.pyramid.game.domain.mouvement.model;

import com.pyramid.game.core.utils.BaseEntity;
import com.pyramid.game.domain.mouvement.model.enums.Operation;
import com.pyramid.game.domain.users.model.AppUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

/**
 * Created by Suvorov Vassilievitch
 * Date: 14/06/2024
 * Time: 19:39
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
@FieldNameConstants
@Entity(name = "PYRAM_MOVEMENT")
public class Mouvement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double credit = 0d;
    private Double debit = 0d;
    @Enumerated(EnumType.STRING)
    private Operation operation;
    private Double balance = 0d;
    @ManyToOne
    @JoinColumn(name = "appuser_id")
    private AppUser appUser;

    private Integer archive = 0;
}
