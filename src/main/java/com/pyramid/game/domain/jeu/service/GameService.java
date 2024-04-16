package com.pyramid.game.domain.jeu.service;

import com.pyramid.game.domain.jeu.model.Game;
import com.pyramid.game.domain.partner.model.Partner;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/04/2024
 * Time: 00:11
 * Project Name: pyramid-game-api
 */
public interface GameService {

    Game createGame(Game game);
    List<Game> listGames();
    Game researchGameById(Long id);
    Game researchGameCode(String code);
    void deleteGame(Long id);
    @Modifying
    Game updateGame(Long id, Game game);
}
