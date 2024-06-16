package com.pyramid.game.domain.mouvement.controller;

import com.pyramid.game.domain.jeu.dto.GameRequest;
import com.pyramid.game.domain.jeu.dto.GameResponse;
import com.pyramid.game.domain.mouvement.dto.MouvementDto;
import com.pyramid.game.domain.mouvement.dto.MouvementRequest;
import com.pyramid.game.domain.mouvement.mapper.MouvementMapper;
import com.pyramid.game.domain.mouvement.service.MouvementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by Suvorov Vassilievitch
 * Date: 14/06/2024
 * Time: 21:18
 * Project Name: pyramid-game-api
 */
@RestController
@RequestMapping("/api/v1/movements")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Mouvement balance Management")
public class MouvementController {

    private final MouvementService mouvementService;
    private final MouvementMapper mapper;

    @PostMapping
    ResponseEntity<MouvementDto> createNewMovement(@Valid @RequestBody MouvementRequest mouvementRequest) {

        return ResponseEntity
                .status(CREATED)
                .body(mapper.toDto(mouvementService.createMouvement(mapper.toEntity(mouvementRequest))));

    }

    @GetMapping("/all")
    ResponseEntity<List<MouvementDto>> listAllMovements() {
        return ResponseEntity.ok(
                mapper.toDtoList(mouvementService.retrieveAllMouvement())
        );
    }

    @GetMapping("/cashier/{login}/{partner}")
    ResponseEntity<List<MouvementDto>> listAllCashierMovements(@PathVariable String login, @PathVariable String partner) {
        return ResponseEntity.ok(
                mapper.toDtoList(mouvementService.retrieveAllUserMouvement(login, partner))
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<MouvementDto> selectMovementId(@PathVariable Long id) {
        return ResponseEntity
                .ok(mapper.toDto(mouvementService.researchMouvement(id)));
    }

    @PutMapping("/{id}")
    ResponseEntity<MouvementDto> updateMovement(@RequestBody MouvementRequest mouvementRequest, @PathVariable Long id) {
        return ResponseEntity
                .ok(mapper.toDto(mouvementService.updateMouvement(id, mapper.toEntity(mouvementRequest))));
    }

    @PutMapping("/{id}/{archive}")
    ResponseEntity<MouvementDto> updateMovementArchive(@PathVariable("id") Long id, @PathVariable("archive") Integer archive) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toDto(mouvementService.updateMouvementArchive(id, archive)));
    }

    @DeleteMapping("/{id}")
    void deleteMovement(@PathVariable Long id){
        mouvementService.deleteMouvement(id);
    }

}
