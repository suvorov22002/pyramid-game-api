package com.pyramid.game.domain.bet.service;

import com.pyramid.game.domain.bet.model.BetKeno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Collection;
import java.util.List;


public interface BetKenoService {

    BetKeno createBet(BetKeno bet);
    BetKeno searchBetPartnerBarcode(String partner, Long barcode);
    List<BetKeno> searchBetCashier(String login);
    Page<BetKeno> searchBetCashierPaginated(String login, Pageable pageable);
    BetKeno searchBetNumero(Long numero);
    List<BetKeno> listAllBetPartner(String codePartner);
    Page<BetKeno> getBetPartnerPaginated(String codePartner, Pageable pageable);
    List<BetKeno> listAllBetPartnerRoom(String codePartner, String designation);
    Page<BetKeno> getBetPartnerRoomPaginated(String codePartner, String designation, Pageable pageable);
    Collection<Object> listAllGameOdds(String game);
    void deleteBetKeno(Long id);
    @Modifying
    BetKeno updateBetKenoArchive(Long id);
    @Modifying
    List<BetKeno> updateAllBetKenoArchive(List<BetKeno> betKenos);
    @Modifying
    BetKeno updateBetKenoMontantGain(Long id, Double montant);
    @Modifying
    BetKeno updateStatus(Long id, String status);
}
