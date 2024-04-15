package com.pyramid.game.domain.jeu.repository;

import com.pyramid.game.domain.jeu.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Game findByCode(String code);
}
