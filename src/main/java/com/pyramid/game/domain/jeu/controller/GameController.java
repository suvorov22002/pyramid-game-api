package com.pyramid.game.domain.jeu.controller;

import com.pyramid.game.domain.jeu.GameMapper;
import com.pyramid.game.domain.jeu.dto.GameRequest;
import com.pyramid.game.domain.jeu.dto.GameResponse;
import com.pyramid.game.domain.jeu.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

}
