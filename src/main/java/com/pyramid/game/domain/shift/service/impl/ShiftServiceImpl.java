package com.pyramid.game.domain.shift.service.impl;

import com.pyramid.game.core.utils.Constants;
import com.pyramid.game.domain.salle.model.Salle;
import com.pyramid.game.domain.shift.mapper.ShiftMapper;
import com.pyramid.game.domain.shift.model.Shift;
import com.pyramid.game.domain.shift.model.ShiftKeno;
import com.pyramid.game.domain.shift.repository.ShiftRepository;
import com.pyramid.game.domain.shift.service.ShiftKenoService;
import com.pyramid.game.domain.shift.service.ShiftService;
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

    /***
     * pour chaque nouveau jeu ajouter son implementation de bordereau de statistique
     */
    @Override
    public Shift createShift(String login, String partner, String salle, LocalDateTime start) {

        List<Shift> shifts = new ArrayList<>();
        LocalDateTime end = LocalDateTime.now();
        Shift shift;

        ShiftKeno shiftKeno = shiftKenoService.extractStactistics(login, partner, salle, start, end);
        shifts.add(shiftKeno);

        //TODO: calcul des balances

        shift = Shift.statistics(shifts);
        shift.setUser(login);
        shift.setPartner(partner);
        shift.setStartDate(start);
        shift.setEndDate(end);

        return shiftRepo.save(shift);
    }

    @Override
    public List<Shift> allShift(String user, String partner) {
        return null;
    }

    @Override
    public List<Shift> allShiftDated(String user, String partner, LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public Shift updateShift(Long id, Shift shift) {
        Shift existingShift = searchShift(id);
        Shift updatedShift = shiftMapper.update(shift, existingShift);
        return shiftRepo.save(updatedShift);
    }

    @Override
    public Shift searchShift(Long id) {
        return shiftRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Constants.SHIFT_NOT_FOUND)
        );
    }
}
