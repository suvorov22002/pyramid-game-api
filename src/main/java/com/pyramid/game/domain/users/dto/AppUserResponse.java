package com.pyramid.game.domain.users.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Suvorov Vassilievitch
 * Date: 21/04/2024
 * Time: 08:38
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
public class AppUserResponse {
    private Long id;
    private String login;
    private String name;
    private String partnerCode;
    private Integer resetPass;
    private String role;
    private Boolean enabled;
    private String phoneNumber;
}
