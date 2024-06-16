package com.pyramid.game.domain.users.model;

import com.pyramid.game.core.utils.BaseEntity;
import com.pyramid.game.core.validation.MandatoryField;
import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.users.model.enums.Role;
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
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uniquePartnerLogin", columnNames = {"partner_id", "login"})
})
@Entity(name = "PYRAM_USER")
public class AppUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MandatoryField
    private String login;

    private String name;

    @MandatoryField
    private String password;

    private Boolean locked = Boolean.FALSE;

    private Boolean enabled = Boolean.TRUE;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role = Role.CASHIER;

    private Integer resetPass = 0;

    @MandatoryField
    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;
    private Double balance = 0d;
}
