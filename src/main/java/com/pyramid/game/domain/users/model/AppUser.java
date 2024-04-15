package com.pyramid.game.domain.users.model;

import com.pyramid.game.core.utils.BaseEntity;
import com.pyramid.game.domain.partner.model.Partner;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

/**
 * Created by Suvorov Vassilievitch
 * Date: 14/04/2024
 * Time: 22:19
 * Project Name: pyramid-game-api
 */

@Getter
@Setter
@FieldNameConstants
@Entity(name = "PYRAM_USER")
public class AppUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;
}
