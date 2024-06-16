package com.pyramid.game.domain.mouvement.repository;

import com.pyramid.game.domain.mouvement.model.Mouvement;
import com.pyramid.game.domain.users.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MouvementRepository extends JpaRepository<Mouvement, Long> {

    List<Mouvement> findByAppUserAndArchiveOrderByCreatedAtDesc(AppUser user, Integer archive);
    List<Mouvement> findByAppUserOrderByCreatedAtDesc(AppUser user);
    List<Mouvement> findByAppUserAndCreatedAtIsBetweenOrderByCreatedAtDesc(AppUser user, LocalDateTime start, LocalDateTime end);
}
