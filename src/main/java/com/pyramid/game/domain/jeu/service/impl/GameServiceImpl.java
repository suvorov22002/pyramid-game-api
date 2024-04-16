package com.pyramid.game.domain.jeu.service.impl;

import com.pyramid.game.core.utils.Constants;
import com.pyramid.game.domain.jeu.GameMapper;
import com.pyramid.game.domain.jeu.model.Game;
import com.pyramid.game.domain.jeu.repository.GameRepository;
import com.pyramid.game.domain.jeu.service.GameService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 16/04/2024
 * Time: 19:59
 * Project Name: pyramid-game-api
 */
@Service
@Transactional
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepo;
    private final GameMapper gameMapper;
    @Override
    public Game createGame(Game game) {

        gameRepo.findByCode(game.getCode()).ifPresent(
             game1 -> {
                 throw new EntityExistsException(Constants.GAME_UNIQUE_CODE);
             }
        );
        System.out.println("COde in: " + game.getCode().toUpperCase());
        game.setCreatedAt(LocalDateTime.now());
        Game g = gameRepo.save(game);
        System.out.println("COde out: " +g.getCode().toUpperCase());
        return g;
    }

    @Override
    public List<Game> listGames() {
        return gameRepo.findAll();
    }

    @Override
    public Game researchGameById(Long id) {
        return gameRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Constants.GAME_NOT_FOUND)
        );
    }

    @Override
    public Game researchGameCode(String code) {
        return gameRepo.findByCode(code).orElseThrow(
                () -> new EntityNotFoundException(Constants.GAME_NOT_FOUND)
        );
    }

    @Override
    public void deleteGame(Long id) {
        Game game = researchGameById(id);
        gameRepo.delete(game);
    }

    @Override
    public Game updateGame(Long id, Game game) {
        Game existingGame = researchGameById(id);
        Game updatedGame = gameMapper.update(game, existingGame);
        return gameRepo.save(updatedGame);
    }
}
