package com.pyramid.game.domain.partner.service;

import com.pyramid.game.domain.partner.model.Parameters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterService{

    Parameters findParameter(Long id);
    Parameters updateParameter(Long id, Parameters parameters);
}
