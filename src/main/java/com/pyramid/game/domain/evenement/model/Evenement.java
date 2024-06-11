package com.pyramid.game.domain.evenement.model;

import com.pyramid.game.core.utils.BaseEntity;
import com.pyramid.game.core.validation.MandatoryField;
import com.pyramid.game.domain.evenement.model.enums.EventStatus;
import com.pyramid.game.domain.jeu.model.Game;
import com.pyramid.game.domain.salle.model.Salle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

/**
 * Created by Suvorov Vassilievitch
 * Date: 09/05/2024
 * Time: 21:31
 * Project Name: pyramid-game-api
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Table(indexes = {
        @Index(name = "numero_tirage_idx", columnList = "game_code, numero_tirage")
})
@Entity(name = "PYRAM_EVENT")
public class Evenement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_code")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "salle_id")
    private Salle salle;

    private LocalDateTime heureTirage;

    private String tirage;

    @MandatoryField
    private Long numeroTirage;

    private Long codeBonus = 1L;

    private Double montantBonus = 0d;

    private Integer multiplicateur = 1;

    @Enumerated(EnumType.STRING)
    private EventStatus status = EventStatus.CANBET;

}
