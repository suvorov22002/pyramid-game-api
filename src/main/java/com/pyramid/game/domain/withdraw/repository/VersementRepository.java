package com.pyramid.game.domain.withdraw.repository;


import com.pyramid.game.domain.withdraw.model.Versement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VersementRepository extends JpaRepository<Versement, Long> {

    List<Versement> findByLoginIgnoreCaseAndCodePartnerIgnoreCase(String login, String partner);
    List<Versement> findByLoginIgnoreCaseAndCodePartnerIgnoreCaseAndCreatedAtBetween(String login, String partner, LocalDateTime start, LocalDateTime end);
    List<Versement> findByLoginIgnoreCaseAndCodePartnerIgnoreCaseAndSalleIgnoreCaseAndCreatedAtBetween(String login, String partner, String salle, LocalDateTime start, LocalDateTime end);
    List<Versement> findByCodePartnerIgnoreCaseAndCreatedAtBetween(String partner, LocalDateTime start, LocalDateTime end);
    List<Versement> findBySalleIgnoreCaseAndCodePartnerIgnoreCaseAndCreatedAtBetween(String salle, String partner, LocalDateTime start, LocalDateTime end);
    Optional<Versement> findByNumeroTicket(Long numeroTicket);
}
