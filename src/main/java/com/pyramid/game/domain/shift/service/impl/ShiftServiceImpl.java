package com.pyramid.game.domain.shift.service.impl;

import com.pyramid.game.core.utils.Constants;
import com.pyramid.game.domain.mouvement.model.Mouvement;
import com.pyramid.game.domain.mouvement.model.enums.Operation;
import com.pyramid.game.domain.mouvement.repository.MouvementRepository;
import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.partner.repository.PartnerRepository;
import com.pyramid.game.domain.salle.model.Salle;
import com.pyramid.game.domain.salle.repository.SalleRepository;
import com.pyramid.game.domain.shift.mapper.ShiftMapper;
import com.pyramid.game.domain.shift.model.Shift;
import com.pyramid.game.domain.shift.model.ShiftKeno;
import com.pyramid.game.domain.shift.model.enums.ShiftStatus;
import com.pyramid.game.domain.shift.repository.ShiftRepository;
import com.pyramid.game.domain.shift.service.ShiftKenoService;
import com.pyramid.game.domain.shift.service.ShiftService;
import com.pyramid.game.domain.users.model.AppUser;
import com.pyramid.game.domain.users.repository.AppUserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 11/06/2024
 * Time: 14:26
 * Project Name: pyramid-game-api
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ShiftServiceImpl implements ShiftService {

    private final ShiftKenoService shiftKenoService;
    private final ShiftRepository shiftRepo;
    private final ShiftMapper shiftMapper;
    private final PartnerRepository partnerRepo;
    private final AppUserRepository appUserRepo;
    private final SalleRepository salleRepo;
    private final MouvementRepository mouvementRepo;

    /***
     * pour chaque nouveau jeu ajouter son implementation de bordereau de statistique
     */
    @Override
    public Shift createShift(Shift shift) {

        Partner partn = partnerRepo.findPartnerByCodePartner(shift.getPartner()).orElseThrow(
                () -> new EntityNotFoundException(Constants.PARTNER_NOT_FOUND)
        );

        AppUser cashier = appUserRepo.findAppUserByPartnerAndLogin(partn, shift.getLogin()).orElseThrow(
                () -> new EntityNotFoundException(Constants.USER_NOT_FOUND)
        );

        Salle room = salleRepo.findSalleByCodeSalle(shift.getSalle()).orElseThrow(
                () -> new EntityNotFoundException(Constants.ROOM_NOT_FOUND)
        );

        // Check if there is row in status OPEN
        List<Shift> shiftOpened
                = shiftRepo.findByLoginIgnoreCaseAndPartnerIgnoreCaseAndSalleIgnoreCaseAndShiftStatusOrderByCreatedAtDesc
                (shift.getLogin(), partn.getDesignation(), room.getDesignation(), ShiftStatus.OPEN);

        if(!shiftOpened.isEmpty()) {
            throw new EntityNotFoundException(Constants.SHIFT_OPEN);
        }

        List<Shift> shifts = new ArrayList<>();
        LocalDateTime end = LocalDateTime.now();
        Shift shift1;

        ShiftKeno shiftKeno = shiftKenoService
                .extractStactistics(shift.getLogin(), shift.getPartner(), shift.getSalle(), shift.getStartDate(), end);
        shifts.add(shiftKeno);

        shift1 = Shift.statistics(shifts);
        shift1.setLogin(shift.getLogin());
        shift1.setPartner(shift.getPartner());
        shift1.setSalle(shift.getSalle());
        shift1.setStartDate(LocalDateTime.now());
        shift1.setEndDate(end);
        shift1.setCreatedAt(LocalDateTime.now());
        shift1.setStartBalance(cashier.getBalance());
        shift1.setShiftStatus(ShiftStatus.OPEN);

        return shiftRepo.save(shift1);
    }

    @Override
    public List<Shift> allShift(String user, String partner) {
        return shiftRepo.findByLoginIgnoreCaseAndPartnerIgnoreCaseOrderByCreatedAtDesc(user, partner);
    }

    @Override
    public List<Shift> allShiftDated(String user, String partner, LocalDateTime start, LocalDateTime end) {
        return shiftRepo.findByLoginIgnoreCaseAndPartnerIgnoreCaseAndCreatedAtBetweenOrderByCreatedAtDesc(user, partner, start, end);
    }

    @Override
    public Shift updateShift(Shift shift) {
        Shift existingShift = summarizeShift(shift);
        existingShift.setShiftStatus(ShiftStatus.CLOSE);

        Partner partn = partnerRepo.findPartnerByCodePartner(existingShift.getPartner()).orElseThrow(
                () -> new EntityNotFoundException(Constants.PARTNER_NOT_FOUND)
        );
        AppUser cashier = appUserRepo.findAppUserByPartnerAndLogin(partn, shift.getLogin()).orElseThrow(
                () -> new EntityNotFoundException(Constants.USER_NOT_FOUND)
        );
        Mouvement mouvement = mouvementRepo
                .findByAppUserAndCreatedAtIsBetweenOrderByCreatedAtDesc(cashier, existingShift.getStartDate(),
                        existingShift.getEndDate()).stream().findFirst().orElse(null);
        Double mouvementBalance = mouvement != null ? mouvement.getBalance() : 0d;
        Mouvement movement = Mouvement.builder()
                .archive(0)
                .balance(mouvementBalance - (existingShift.getTotalPayin() - existingShift.getTotalPayout()))
                .operation(Operation.CLOSE_SHIFT)
                .credit(mouvementBalance)
                .debit(existingShift.getTotalPayin() - existingShift.getTotalPayout())
                .appUser(cashier)
                .build();
        movement.setCreatedAt(LocalDateTime.now());
        mouvementRepo.save(movement);

        return shiftRepo.save(existingShift);
    }

    @Override
    public Shift summarizeShift(Shift shift) {

        Partner partn = partnerRepo.findPartnerByCodePartner(shift.getPartner()).orElseThrow(
                () -> new EntityNotFoundException(Constants.PARTNER_NOT_FOUND)
        );

        AppUser cashier = appUserRepo.findAppUserByPartnerAndLogin(partn, shift.getLogin()).orElseThrow(
                () -> new EntityNotFoundException(Constants.USER_NOT_FOUND)
        );

        Salle room = salleRepo.findSalleByCodeSalle(shift.getSalle()).orElseThrow(
                () -> new EntityNotFoundException(Constants.ROOM_NOT_FOUND)
        );

        // Check if there is row in status OPEN
        List<Shift> shiftOpened
                = shiftRepo.findByLoginIgnoreCaseAndPartnerIgnoreCaseAndSalleIgnoreCaseAndShiftStatusOrderByCreatedAtDesc
                (shift.getLogin(), shift.getPartner(), shift.getSalle(), ShiftStatus.OPEN);

        Shift existingShift = shiftOpened.stream().findFirst().orElseThrow(
                () -> new EntityNotFoundException(Constants.SHIFT_NOT_OPEN)
        );

        List<Shift> shifts = new ArrayList<>();
        LocalDateTime end = LocalDateTime.now();
        Shift shift1;

        ShiftKeno shiftKeno = shiftKenoService
                .extractStactistics(shift.getLogin(), partn.getDesignation(), room.getDesignation(),
                        existingShift.getStartDate(), end);
        shifts.add(shiftKeno);

        shift1 = Shift.statistics(shifts);
        existingShift.setTotalPayin(shift1.getTotalPayin());
        existingShift.setTotalSlip(shift1.getTotalSlip());
        existingShift.setTotalPayout(shift1.getTotalPayout());
        existingShift.setPaidSlip(shift1.getPaidSlip());
        existingShift.setEndDate(LocalDateTime.now());
        existingShift.setEndBalance(cashier.getBalance());

        return existingShift;
    }

    @Override
    public Shift searchShift(Long id) {
        return shiftRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Constants.SHIFT_NOT_FOUND)
        );
    }

    @Override
    public Shift searchShift(Shift shift) {
        List<Shift> shiftOpened
                = shiftRepo.findByLoginIgnoreCaseAndPartnerIgnoreCaseAndSalleIgnoreCaseAndShiftStatusOrderByCreatedAtDesc
                (shift.getLogin(), shift.getPartner(), shift.getSalle(), shift.getShiftStatus());
        return shiftOpened.stream().findFirst().orElse(null);
    }
}
