package com.pyramid.game.domain.partner.dto;

import com.pyramid.game.core.validation.MandatoryField;
import com.pyramid.game.domain.partner.model.Enrollment;
import com.pyramid.game.domain.salle.model.Salle;
import com.pyramid.game.domain.users.model.AppUser;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/04/2024
 * Time: 09:17
 * Project Name: pyramid-game-api
 */
public record PartnerRequest(
        String designation,
        String localisation,
        String status,
        Set<EnrollmentRequest> enrollments
) { }
