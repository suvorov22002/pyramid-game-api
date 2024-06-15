package com.pyramid.game.domain.shift.service.impl;

import com.pyramid.game.domain.bet.model.BetKeno;
import com.pyramid.game.domain.bet.repository.BetKenoRepository;
import com.pyramid.game.domain.shift.model.ShiftKeno;
import com.pyramid.game.domain.shift.service.ShiftKenoService;
import com.pyramid.game.domain.withdraw.model.Versement;
import com.pyramid.game.domain.withdraw.repository.VersementRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 12/06/2024
 * Time: 00:47
 * Project Name: pyramid-game-api
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ShiftKenoServiceImpl implements ShiftKenoService {

    private final BetKenoRepository betKenoRepo;
    private final VersementRepository versementRepo;

    @Override
    public ShiftKeno extractStactistics(String login, String partner, String salle, LocalDateTime start, LocalDateTime end) {

        List<BetKeno> betKenoList = betKenoRepo
                .findByCashierLoginIgnoreCaseAndCodePartnerIgnoreCaseAndSalleIgnoreCaseAndCreatedAtBetween(
                     login, partner, salle, start, end);
        var totPayin = betKenoList.stream().map(BetKeno::getMontantMise).reduce(Double::sum).orElse(0d);
        List<Versement> versementList = versementRepo
                .findByLoginIgnoreCaseAndCodePartnerIgnoreCaseAndSalleIgnoreCaseAndCreatedAtBetween(
                        login, partner, salle, start, end);
        var totPayout = versementList.stream().map(Versement::getMontantVers).reduce(Double::sum).orElse(0d);

        return (ShiftKeno) ShiftKeno.builder()
                .totalPayin(totPayin)
                .totalSlip(betKenoList.size())
                .totalPayout(totPayout)
                .paidSlip(versementList.size())
                .build();

    }
}
