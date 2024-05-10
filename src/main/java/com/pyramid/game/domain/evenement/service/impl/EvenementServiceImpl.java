package com.pyramid.game.domain.evenement.service.impl;

import com.pyramid.game.core.utils.Constants;
import com.pyramid.game.domain.evenement.model.Evenement;
import com.pyramid.game.domain.evenement.repository.EvenementRepository;
import com.pyramid.game.domain.evenement.service.EvenementService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by Suvorov Vassilievitch
 * Date: 09/05/2024
 * Time: 21:51
 * Project Name: pyramid-game-api
 */
@Service
@Transactional
@RequiredArgsConstructor
public class EvenementServiceImpl implements EvenementService {

    private final EvenementRepository evenementRepo;

    @Override
    public Evenement addEvent(Evenement evenement) {
        return evenementRepo.save(evenement);
    }

    @Override
    public Evenement updateEvent(Evenement evenement) {
        evenementRepo.findById(evenement.getId()).ifPresentOrElse(
                ev -> {
                    ev.setHeureTirage(evenement.getHeureTirage());
                    ev.setMultiplicateur(evenement.getMultiplicateur());
                    ev.setMontantBonus(evenement.getMontantBonus());
                    ev.setTirage(evenement.getTirage());
                    ev.setNumeroTirage(evenement.getNumeroTirage());
                    ev.setMultiplicateur(evenement.getMultiplicateur());
                    evenementRepo.save(ev);
                },
                () -> { throw new EntityNotFoundException(Constants.EVENT_NOT_FOUND); }
        );
        return evenement;
    }
}
