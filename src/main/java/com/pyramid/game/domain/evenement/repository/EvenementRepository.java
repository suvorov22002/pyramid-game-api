package com.pyramid.game.domain.evenement.repository;

import com.pyramid.game.domain.evenement.model.Evenement;
import com.pyramid.game.domain.jeu.model.Game;
import com.pyramid.game.domain.salle.model.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM PYRAM_EVENT WHERE game_code=:game_code AND salle_id=:salle_id ORDER BY numero_tirage DESC LIMIT 1")
    Optional<Evenement> findMaxEvent(@Param("game_code") Long game_code, @Param("salle_id") Long salle_id);

    List<Evenement> findEvenementBySalleAndGame(Salle salle, Game game);
}
