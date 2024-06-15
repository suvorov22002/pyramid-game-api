package com.pyramid.game.domain.bet.service;

import com.pyramid.game.domain.bet.model.BetKeno;
import com.pyramid.game.domain.withdraw.model.Versement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


public interface BetKenoService {

    BetKeno createBet(BetKeno bet);
    BetKeno searchBetPartnerBarcode(String partner, Long barcode);
    List<BetKeno> searchBetCashier(String login, String partner);
    Page<BetKeno> searchBetCashierPaginated(String login, String partner, Pageable pageable);
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

    List<BetKeno> listAllUserBetDated(String login, String partner, LocalDateTime start, LocalDateTime end);
    List<BetKeno> listAllUserBetRoomDated(String login, String partner, String salle, LocalDateTime start, LocalDateTime end);
    List<BetKeno> listAllPartnerBetDated(String partner, LocalDateTime start, LocalDateTime end);
    List<BetKeno> listAllPartnerBetRoomDated(String salle, String partner, LocalDateTime start, LocalDateTime end);
}
