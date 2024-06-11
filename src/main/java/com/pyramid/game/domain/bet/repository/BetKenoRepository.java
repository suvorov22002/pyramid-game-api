package com.pyramid.game.domain.bet.repository;

import com.pyramid.game.domain.bet.model.BetKeno;
import com.pyramid.game.domain.partner.model.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BetKenoRepository extends JpaRepository<BetKeno, Long> {
    Optional<BetKeno> findByCodePartnerAndBarcode(String codePartner, Long barcode);
    List<BetKeno> findByCashierLoginIgnoreCase(String login);
    Optional<BetKeno> findByNumeroTicket(Integer numeroTicket);
    List<BetKeno> findByNumeroTirage(Integer numeroTirage);
    List<BetKeno> findByCodePartnerIgnoreCase(String codePartner);
    List<BetKeno> findByCodePartnerIgnoreCaseAndSalleIgnoreCase(String codePartner, String designation);
    Page<BetKeno> findAllByCashierLoginOrderByCreatedAtDesc(String login, Pageable pageable);
    Page<BetKeno> findAllByCodePartnerOrderByCreatedAtDesc(String partner, Pageable pageable);
    Page<BetKeno> findAllByCodePartnerIgnoreCaseAndSalleIgnoreCaseOrderByCreatedAtDesc(String partner, String salle, Pageable pageable);
}
