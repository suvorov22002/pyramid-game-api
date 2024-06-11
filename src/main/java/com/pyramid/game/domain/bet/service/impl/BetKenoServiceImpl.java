package com.pyramid.game.domain.bet.service.impl;

import com.pyramid.game.core.config.Pari;
import com.pyramid.game.core.utils.Constants;
import com.pyramid.game.domain.bet.model.BetKeno;
import com.pyramid.game.domain.bet.model.enums.BetStatus;
import com.pyramid.game.domain.bet.repository.BetKenoRepository;
import com.pyramid.game.domain.bet.service.BetKenoService;
import com.pyramid.game.domain.evenement.model.Evenement;
import com.pyramid.game.domain.evenement.model.enums.EventStatus;
import com.pyramid.game.domain.evenement.repository.EvenementRepository;
import com.pyramid.game.domain.jeu.model.Game;
import com.pyramid.game.domain.jeu.model.enums.GameStatus;
import com.pyramid.game.domain.jeu.repository.GameRepository;
import com.pyramid.game.domain.partner.model.Enrollment;
import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.partner.model.enums.EnrollStatus;
import com.pyramid.game.domain.partner.model.enums.PartnerStatus;
import com.pyramid.game.domain.partner.repository.EnrollmentRepository;
import com.pyramid.game.domain.partner.repository.PartnerRepository;
import com.pyramid.game.domain.salle.model.Salle;
import com.pyramid.game.domain.salle.model.enums.SalleStatus;
import com.pyramid.game.domain.salle.repository.SalleRepository;
import com.pyramid.game.domain.slip.model.SlipKeno;
import com.pyramid.game.domain.slip.repository.SlipKenoRepository;
import com.pyramid.game.domain.slip.service.SlipKenoService;
import com.pyramid.game.domain.users.model.AppUser;
import com.pyramid.game.domain.users.model.enums.Role;
import com.pyramid.game.domain.users.repository.AppUserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Suvorov Vassilievitch
 * Date: 10/05/2024
 * Time: 22:22
 * Project Name: pyramid-game-api
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BetKenoServiceImpl implements BetKenoService {

    private final BetKenoRepository betKenoRepo;
    private final PartnerRepository partnerRepo;
    private final GameRepository gameRepo;
    private final SalleRepository salleRepo;
    private final AppUserRepository appUserRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final EvenementRepository evenementRepo;
    private final SlipKenoRepository slipKenoRepo;
    private final SlipKenoService slipKenoService;
    private final Pari pari;

    @Override
    public BetKeno createBet(BetKeno bet) {

        // Verification existence du partenaire
        Partner partner = partnerRepo.findPartnerByCodePartner(bet.getCodePartner()).orElseThrow(
                () -> new EntityNotFoundException(Constants.PARTNER_NOT_FOUND)
        );

        // Verification de la salle
        Salle salle = salleRepo.findSalleByPartnerAndCodeSalle(partner, bet.getSalle()).orElseThrow(
                () -> new EntityNotFoundException(Constants.ROOM_NOT_FOUND)
        );

        if(salle.getStatus() != SalleStatus.ACTIVE) {
            throw new IllegalStateException(Constants.ROOM_NOT_ACTIVE);
        }

        // Verification existence du jeu activé chez le partenaire
        Game game = gameRepo.findByCode(bet.getCodeGame()).orElseThrow(
                () -> new EntityNotFoundException(Constants.GAME_NOT_FOUND)
        );

        if(game.getStatus() == GameStatus.INACTIVE) {
            throw new IllegalStateException(Constants.GAME_NOT_ACTIVE);
        }
        Enrollment enrollment = enrollmentRepo.findEnrollmentByPartnerAndGame(partner, game).orElseThrow(
                () -> new EntityNotFoundException(Constants.ENROLLMENT_NOT_FOUND)
        );

        if(enrollment.getStatus() != EnrollStatus.ENROLLED) {
            throw new IllegalStateException(Constants.ENROLLMENT_NOT_ACTIVE);
        }

        // Verification du casier
        AppUser user = appUserRepo.findAppUserByPartnerAndLogin(partner, bet.getCashierLogin()).orElseThrow(
                () -> new EntityNotFoundException(Constants.CASHIER_NOT_FOUND)
        );

        if(user.getRole() != Role.CASHIER) {
            throw new IllegalStateException(Constants.USER_NOT_CASHIER);
        }

        // Verification de l'existence de l'evenement
        Evenement event = evenementRepo.findMaxEvent(game.getId(), salle.getId()).orElseThrow(
                () -> new EntityNotFoundException(Constants.EVENT_NOT_FOUND)
        );

        if(event.getStatus() != EventStatus.CANBET) {
            throw new IllegalStateException(Constants.EVENT_NOT_UNKNOWNDRAW);
        }

        if(!Objects.equals(event.getNumeroTirage(), bet.getNumeroTirage())) {
            throw new IllegalStateException(Constants.EVENT_NOT_UNKNOWNDRAW);
        }

        // TODO: check cashier balance and bet amount threshold

        String[] betSelections;

        // Recuperation de la cote
        var codePari = pari.getListPari().get("keno");
        Map<String, String> res = (Map<String, String>) codePari.get(bet.getCodePari());
        var choice = Integer.parseInt(res.get("choice"));
        log.info("Choice: {}", choice);
        var cote = res.get("cote");
        var selection = res.get("designation");
        String[] cotes = cote.split("_");
        log.info("Cotes: {}", Arrays.toString(cotes));

        var odd = switch (choice) {
            case 0 -> {
                bet.setSelection(selection);
                yield cotes[0];
            }
            case 1, 2 -> {
                betSelections = bet.getSelection().split("-");
                yield cotes[betSelections.length];
            }
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };

        log.info("Odd: {}", odd);

        // Mise à jour du montant bonus
        Double bonusAmount = event.getMontantBonus();
        Long codeBonus = event.getCodeBonus();
        bonusAmount += (enrollment.getBonusRate() / 100) * bet.getMontantMise();
        event.setMontantBonus(bonusAmount);
        event.setCodeBonus(++codeBonus);
        evenementRepo.save(event);

        bet.setCreatedAt(LocalDateTime.now());
        bet.setCodeGame(game.getDesignation());
        bet.setCodePartner(partner.getDesignation());
        bet.setSalle(salle.getDesignation());
        bet.setOdds(Double.valueOf(odd));
        bet.setNumeroTirage(event.getNumeroTirage());
        bet.setBarcode(Constants.generateBarcode());
        bet.setCodeBonus(codeBonus);

        // Creation of slip
        SlipKeno slipKeno;
        List<SlipKeno> slipKenos = new ArrayList<>();

        Double miseSelectionMin = bet.getMontantMise() / bet.getMultipleRound();
        miseSelectionMin = (double)((int)(miseSelectionMin*100))/100;

        for(int i = 0; i < bet.getMultipleRound(); i++) {

            slipKeno = SlipKeno.builder()
                    .betKeno(bet)
                    .odds(Double.valueOf(odd))
                    .selection(bet.getSelection())
                    .codePari(bet.getCodePari())
                    .montantSelection(miseSelectionMin)
                    .numeroTirage(i + event.getNumeroTirage())
                    .build();

            slipKenos.add(slipKeno);

        }

        bet.setSlips(slipKenoService.createListSlip(slipKenos));

        return betKenoRepo.save(bet);
    }

    @Override
    public BetKeno searchBetPartnerBarcode(String partner, Long barcode) {

        partnerRepo.findPartnerByDesignation(partner).orElseThrow(
                () -> new EntityNotFoundException(Constants.PARTNER_NOT_FOUND)
        );

        return betKenoRepo.findByCodePartnerAndBarcode(partner, barcode).orElseThrow(
                () -> new EntityNotFoundException(Constants.BET_UNKNOWN)
        );

    }

    @Override
    public List<BetKeno> searchBetCashier(String login) {

        return betKenoRepo.findByCashierLoginIgnoreCase(login);
    }

    @Override
    public Page<BetKeno> searchBetCashierPaginated(String login, Pageable pageable) {
        return betKenoRepo.findAllByCashierLoginOrderByCreatedAtDesc(login, pageable);
    }

    @Override
    public BetKeno searchBetNumero(Long numero) {

        return betKenoRepo.findById(numero).orElseThrow(
                () -> new EntityNotFoundException(Constants.BET_UNKNOWN)
        );
    }

    @Override
    public List<BetKeno> listAllBetPartner(String codePartner) {
        return betKenoRepo.findByCodePartnerIgnoreCase(codePartner);
    }

    @Override
    public Page<BetKeno> getBetPartnerPaginated(String codePartner, Pageable pageable) {
        return betKenoRepo.findAllByCodePartnerOrderByCreatedAtDesc(codePartner, pageable);
    }

    @Override
    public List<BetKeno> listAllBetPartnerRoom(String codePartner, String designation) {
        return betKenoRepo.findByCodePartnerIgnoreCaseAndSalleIgnoreCase(codePartner, designation);
    }

    @Override
    public Page<BetKeno> getBetPartnerRoomPaginated(String codePartner, String designation, Pageable pageable) {
        return betKenoRepo
                .findAllByCodePartnerIgnoreCaseAndSalleIgnoreCaseOrderByCreatedAtDesc(codePartner, designation, pageable);
    }

    @Override
    public Collection<Object> listAllGameOdds(String game) {

        return pari.getListPari().get(game).values();
    }

    @Override
    public void deleteBetKeno(Long id) {
        betKenoRepo.findById(id).ifPresentOrElse(
                betKenoRepo::delete,
                () -> {
                    throw new EntityNotFoundException(Constants.BET_UNKNOWN);
                }
        );

    }

    @Override
    public BetKeno updateBetKenoArchive(Long id) {
        BetKeno betKeno = betKenoRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Constants.BET_UNKNOWN)
        );

        betKeno.setArchive(1);
        betKeno.setUpdatedAt(LocalDateTime.now());
        return betKenoRepo.save(betKeno);
    }

    @Override
    public List<BetKeno> updateAllBetKenoArchive(List<BetKeno> betKenos) {
        List<BetKeno> newBetKenos = betKenos.stream().peek(e -> {
            e.setArchive(1);
            e.setUpdatedAt(LocalDateTime.now());
        }).toList();

        return betKenoRepo.saveAll(newBetKenos);
    }

    @Override
    public BetKeno updateBetKenoMontantGain(Long id, Double montant) {

        BetKeno betKeno = betKenoRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Constants.BET_UNKNOWN)
        );

        Double currentWin = betKeno.getMontantGain() + montant;
        betKeno.setMontantGain(currentWin);
        return betKenoRepo.save(betKeno);
    }

    @Override
    public BetKeno updateStatus(Long id, String status) {
        BetKeno betKeno = searchBetNumero(id);
        betKeno.setStatus(BetStatus.valueOf(status));
        return betKenoRepo.save(betKeno);
    }
}
