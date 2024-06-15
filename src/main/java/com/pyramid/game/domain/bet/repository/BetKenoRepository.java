package com.pyramid.game.domain.bet.repository;

import com.pyramid.game.core.utils.Constants;
import com.pyramid.game.domain.bet.model.BetKeno;
import com.pyramid.game.domain.partner.model.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface BetKenoRepository extends JpaRepository<BetKeno, Long> {
    Optional<BetKeno> findByCodePartnerAndBarcode(String codePartner, Long barcode);
    List<BetKeno> findByCashierLoginIgnoreCaseAndCodePartnerIgnoreCase(String login, String partner);
    Optional<BetKeno> findByNumeroTicket(Integer numeroTicket);
    List<BetKeno> findByNumeroTirage(Integer numeroTirage);
    List<BetKeno> findByCodePartnerIgnoreCase(String codePartner);
    List<BetKeno> findByCodePartnerIgnoreCaseAndSalleIgnoreCase(String codePartner, String designation);
    Page<BetKeno> findAllByCashierLoginIgnoreCaseAndCodePartnerIgnoreCaseOrderByCreatedAtDesc(String login, String codePartner, Pageable pageable);
    Page<BetKeno> findAllByCodePartnerOrderByCreatedAtDesc(String partner, Pageable pageable);
    Page<BetKeno> findAllByCodePartnerIgnoreCaseAndSalleIgnoreCaseOrderByCreatedAtDesc(String partner, String salle, Pageable pageable);
    List<BetKeno> findByCashierLoginIgnoreCaseAndCodePartnerIgnoreCaseAndCreatedAtBetween(String login, String partner, LocalDateTime start, LocalDateTime end);
    List<BetKeno> findByCashierLoginIgnoreCaseAndCodePartnerIgnoreCaseAndSalleIgnoreCaseAndCreatedAtBetween(String login, String partner, String salle, LocalDateTime start, LocalDateTime end);
    List<BetKeno> findByCodePartnerIgnoreCaseAndCreatedAtBetween(String partner, LocalDateTime start, LocalDateTime end);
    List<BetKeno> findBySalleIgnoreCaseAndCodePartnerIgnoreCaseAndCreatedAtBetween(String salle, String partner, LocalDateTime start, LocalDateTime end);
    @Query(value = Constants.STAT_KENO, nativeQuery = true)
    List<Map<String, Object>> findStatistic(String login, String partner, String salle, LocalDateTime start, LocalDateTime end);
}
