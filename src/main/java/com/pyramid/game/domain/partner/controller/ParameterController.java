package com.pyramid.game.domain.partner.controller;

import com.pyramid.game.domain.partner.dto.ParameterRequest;
import com.pyramid.game.domain.partner.dto.ParameterResponse;
import com.pyramid.game.domain.partner.mapper.ParameterMapper;
import com.pyramid.game.domain.partner.service.ParameterService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Suvorov Vassilievitch
 * Date: 21/04/2024
 * Time: 00:02
 * Project Name: pyramid-game-api
 */
@RestController
@RequestMapping("/api/v1/parameters")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Global Partner Parameters Management")
public class ParameterController {

    private final ParameterService parameterService;
    private final ParameterMapper mapper;

    @GetMapping("/{id}")
    ResponseEntity<ParameterResponse> selectParameter(@PathVariable Long id){
        return ResponseEntity
                .ok(mapper.toDto(parameterService.findParameter(id)));
    }

    @PutMapping("/{id}")
    ResponseEntity<ParameterResponse> updateParameter(@Valid @RequestBody ParameterRequest parameterRequest, @PathVariable Long id){
        return ResponseEntity
                .ok(mapper.toDto(parameterService.updateParameter(id, mapper.toEntity(parameterRequest))));
    }
}
