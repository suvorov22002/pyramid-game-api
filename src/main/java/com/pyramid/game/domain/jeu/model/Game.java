package com.pyramid.game.domain.jeu.model;

import com.pyramid.game.core.utils.BaseEntity;
import com.pyramid.game.core.validation.MandatoryField;
import com.pyramid.game.domain.jeu.model.enums.GameStatus;
import com.pyramid.game.domain.partner.model.Enrollment;
import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.salle.model.Salle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/04/2024
 * Time: 00:04
 * Project Name: pyramid-game-api
 */

@Getter
@Setter
@FieldNameConstants
@Entity(name = "PYRAM_GAME")
public class Game extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Length(min = 4, max = 4, message = "Code length should be equal to 4.")
    @MandatoryField
    private String code;

    private String designation;

    private String description;

    @Enumerated(EnumType.STRING)
    private GameStatus status = GameStatus.ACTIVE;

    @OneToMany(mappedBy = "game")
    Set<Enrollment> enrollments;
}
