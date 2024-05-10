package com.pyramid.game.domain.users.service;

import com.pyramid.game.domain.users.dto.AppUserRequest;

import java.util.Map;

public interface AuthService {

    Map<String, String> login(AppUserRequest authRequest);

}
