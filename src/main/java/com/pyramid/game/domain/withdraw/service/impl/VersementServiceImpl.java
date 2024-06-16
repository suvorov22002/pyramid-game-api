package com.pyramid.game.domain.withdraw.service.impl;

import com.pyramid.game.core.utils.Constants;
import com.pyramid.game.domain.withdraw.model.Versement;
import com.pyramid.game.domain.withdraw.repository.VersementRepository;
import com.pyramid.game.domain.withdraw.service.VersementService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 11/06/2024
 * Time: 09:21
 * Project Name: pyramid-game-api
 */
@Service
@Transactional
@RequiredArgsConstructor
public class VersementServiceImpl implements VersementService {

    private final VersementRepository versementRepo;

    @Override
    public List<Versement> listAllUserDraw(String login, String partner) {
        return versementRepo.findByLoginIgnoreCaseAndCodePartnerIgnoreCase(login, partner);
    }

    @Override
    public List<Versement> listAllUserDrawDated(String login, String partner, LocalDateTime start, LocalDateTime end) {
        return versementRepo.findByLoginIgnoreCaseAndCodePartnerIgnoreCaseAndCreatedAtBetween(login, partner, start, end);
    }

    @Override
    public List<Versement> listAllUserDrawRoomDated(String login, String partner, String salle, LocalDateTime start, LocalDateTime end) {
        return versementRepo.findByLoginIgnoreCaseAndCodePartnerIgnoreCaseAndSalleIgnoreCaseAndCreatedAtBetween(login, partner, salle, start, end);
    }

    @Override
    public List<Versement> listAllPartnerDrawDated(String partner, LocalDateTime start, LocalDateTime end) {
        return versementRepo.findByCodePartnerIgnoreCaseAndCreatedAtBetween(partner, start, end);
    }

    @Override
    public List<Versement> listAllPartnerDrawRoomDated(String salle, String partner, LocalDateTime start, LocalDateTime end) {
        return versementRepo.findBySalleIgnoreCaseAndCodePartnerIgnoreCaseAndCreatedAtBetween(salle, partner, start, end);
    }

    @Override
    public Versement selectVersement(Long numeroTicket) {
        return versementRepo.findByNumeroTicket(numeroTicket).orElseThrow(
                () -> new EntityNotFoundException(Constants.WITHDRAW_NOT_FOUND)
        );
    }

    @Override
    public Versement createWithdrawal(Versement versement) {
        return versementRepo.save(versement);
    }
}
