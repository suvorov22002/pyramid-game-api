package com.pyramid.game.domain.bet.repository;

import com.pyramid.game.domain.bet.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BetRepository extends JpaRepository<Bet, Long> {

    Optional<Bet> findByCodePartnerAndBarcodeIgnoreCase(String codePartner, String barcode);
}
