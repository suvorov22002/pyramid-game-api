package com.pyramid.game.domain.evenement.conroller;

import com.pyramid.game.domain.evenement.dto.EvenementRequest;
import com.pyramid.game.domain.evenement.dto.EvenementResponse;
import com.pyramid.game.domain.evenement.mapper.EvenementMapper;
import com.pyramid.game.domain.evenement.service.EvenementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 12/05/2024
 * Time: 14:32
 * Project Name: pyramid-game-api
 */
@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Events Management")
public class EvenementController {

    private final EvenementService evenementService;
    private final EvenementMapper mapper;

    @PostMapping
    ResponseEntity<EvenementResponse> createEvent(@Valid @RequestBody EvenementRequest evenementRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toDto(evenementService.addEvent(mapper.toEntity(evenementRequest))));

    }

    @GetMapping("/game/{game}/room/{room}")
    ResponseEntity<List<EvenementResponse>> selectAllEventRoomGame(@PathVariable String game, @PathVariable String room) {
        return ResponseEntity
                .ok(mapper.toDtoList(evenementService.listAllEventPartnerGame(room, game)));
    }


}
