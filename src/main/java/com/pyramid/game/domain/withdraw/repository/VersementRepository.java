package com.pyramid.game.domain.withdraw.repository;


import com.pyramid.game.domain.withdraw.model.Versement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VersementRepository extends JpaRepository<Versement, Long> {

    List<Versement> findByLoginAndCodePartner(String login, String partner);
    List<Versement> findByLoginAndCodePartnerAndCreatedAtBetween(String login, String partner, LocalDateTime start, LocalDateTime end);
    List<Versement> findByLoginAndCodePartnerAndSalleAndCreatedAtBetween(String login, String partner, String salle, LocalDateTime start, LocalDateTime end);
    List<Versement> findByCodePartnerAndCreatedAtBetween(String partner, LocalDateTime start, LocalDateTime end);
    List<Versement> findBySalleAndCodePartnerAndCreatedAtBetween(String salle, String partner, LocalDateTime start, LocalDateTime end);
    Optional<Versement> findByNumeroTicket(Long numeroTicket);
}
