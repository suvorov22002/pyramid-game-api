package com.pyramid.game.domain.partner.service.impl;

import com.pyramid.game.core.utils.Constants;
import com.pyramid.game.domain.partner.mapper.ParameterMapper;
import com.pyramid.game.domain.partner.model.Parameters;
import com.pyramid.game.domain.partner.repository.ParametersRepository;
import com.pyramid.game.domain.partner.service.ParameterService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;

/**
 * Created by Suvorov Vassilievitch
 * Date: 21/04/2024
 * Time: 00:19
 * Project Name: pyramid-game-api
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ParameterServiceImplement implements ParameterService {

    private final ParametersRepository parametersRepo;
    private final ParameterMapper parameterMapper;
    @Override
    public Parameters findParameter(Long id) {

        return parametersRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Constants.PARAMETER_EXCEPTION)
        );
    }

    @Override
    public Parameters updateParameter(Long id, Parameters parameters) {
        Parameters existingParameters = findParameter(id);
        parameters.setUpdatedAt(LocalDateTime.now());
        Parameters param = parameterMapper.update(parameters, existingParameters);
        return parametersRepo.save(param);
    }
}
