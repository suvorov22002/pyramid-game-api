package com.pyramid.game.domain.partner.model;

import com.pyramid.game.core.utils.BaseEntity;
import com.pyramid.game.core.validation.MandatoryField;
import com.pyramid.game.domain.partner.model.enums.PartnerStatus;
import com.pyramid.game.domain.salle.model.Salle;
import com.pyramid.game.domain.users.model.AppUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.mapstruct.Builder;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Suvorov Vassilievitch
 * Date: 14/04/2024
 * Time: 22:02
 * Project Name: pyramid-game-api
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Entity(name = "PYRAM_PARTNER")
public class Partner extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @MandatoryField
    private String codePartner;

    private String designation;

    private String localisation;

    @Enumerated(EnumType.STRING)
    private PartnerStatus status = PartnerStatus.INACTIVE;

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    Set<Enrollment> enrollments = new HashSet<>();  // If empty, partner is considered as deactivated

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Salle> salles = new HashSet<>();

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<AppUser> users = new HashSet<>();

    @OneToOne(mappedBy = "partner", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Parameters parameters;
}
