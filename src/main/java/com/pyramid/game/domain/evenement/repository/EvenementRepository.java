package com.pyramid.game.domain.evenement.repository;

import com.pyramid.game.domain.evenement.model.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {
}
