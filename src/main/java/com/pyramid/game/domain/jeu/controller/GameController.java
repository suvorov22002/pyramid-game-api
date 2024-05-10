package com.pyramid.game.domain.jeu.controller;

import com.pyramid.game.domain.jeu.mapper.GameMapper;
import com.pyramid.game.domain.jeu.dto.GameRequest;
import com.pyramid.game.domain.jeu.dto.GameResponse;
import com.pyramid.game.domain.jeu.service.GameService;
import com.pyramid.game.domain.partner.dto.PartnerResponse;
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
 * Date: 16/04/2024
 * Time: 21:00
 * Project Name: pyramid-game-api
 */
@RestController
@RequestMapping("/api/v1/games")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Game Management")
public class GameController {

    private final GameService gameService;
    private final GameMapper gameMapper;

    @PostMapping
    ResponseEntity<GameResponse> createNewGame(@Valid @RequestBody GameRequest gameRequest) {

        return ResponseEntity
                .status(CREATED)
                .body(gameMapper.toDto(gameService.createGame(gameMapper.toEntity(gameRequest))));

    }

    @GetMapping("/all")
    ResponseEntity<List<GameResponse>> listAllGames() {
        return ResponseEntity.ok(
                gameMapper.toDtoList(gameService.listGames())
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<GameResponse> selectGameId(@PathVariable Long id) {
        return ResponseEntity
                .ok(gameMapper.toDto(gameService.researchGameById(id)));
    }

    @GetMapping("/code/{code}")
    ResponseEntity<GameResponse> selectGameCode(@PathVariable String code) {
        return ResponseEntity
                .ok(gameMapper.toDto(gameService.researchGameCode(code)));
    }

    @DeleteMapping("/{id}")
    void deleteGame(@PathVariable Long id){
        gameService.deleteGame(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<GameResponse> updateGame(@RequestBody GameRequest gameRequest, @PathVariable Long id) {
        return ResponseEntity
                .ok(gameMapper.toDto(gameService.updateGame(id, gameMapper.toEntity(gameRequest))));
    }

    @PutMapping("/{id}/{status}")
    ResponseEntity<GameResponse> updateGameStatus(@PathVariable("id") Long id, @PathVariable("status") String status) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(gameMapper.toDto(gameService.updateGameStatus(id, status)));
    }

}
