package com.pyramid.game.domain.users.dto;

import com.pyramid.game.core.validation.MandatoryField;

/**
 * Created by Suvorov Vassilievitch
 * Date: 21/04/2024
 * Time: 08:34
 * Project Name: pyramid-game-api
 */
public record AppUserRequest (
        @MandatoryField
        String login,
        String name,
        @MandatoryField
        String password,
        @MandatoryField
        String partnerCode,
        String phoneNumber,
        String role,
        String email
) { }
