package com.pyramid.game.domain.bet.service.impl;

import com.pyramid.game.core.config.Pari;
import com.pyramid.game.core.utils.Constants;
import com.pyramid.game.domain.bet.model.Bet;
import com.pyramid.game.domain.bet.model.BetKeno;
import com.pyramid.game.domain.bet.model.enums.BetStatus;
import com.pyramid.game.domain.bet.repository.BetKenoRepository;
import com.pyramid.game.domain.bet.repository.BetRepository;
import com.pyramid.game.domain.bet.service.BetService;
import com.pyramid.game.domain.partner.repository.PartnerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by Suvorov Vassilievitch
 * Date: 16/06/2024
 * Time: 16:48
 * Project Name: pyramid-game-api
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BetServiceImpl implements BetService {

    private final BetRepository betRepo;
    private final Pari pari;
    private final BetKenoRepository betKenoRepo;
    private final PartnerRepository partnerRepo;

    @Override
    public Bet createBet(Bet bet) {
        bet.setCreatedAt(LocalDateTime.now());
        return betRepo.save(bet);
    }

    @Override
    public Object selectBet(String partner, String barcode) {

        Bet bet = betRepo.findByCodePartnerAndBarcodeIgnoreCase(partner, barcode)
                .orElseThrow(
                        () -> new EntityNotFoundException(Constants.BET_UNKNOWN)
                );

        return switch (bet.getCodeGame()) {
            case "KENO" -> {

                BetKeno betKeno =  betKenoRepo.findByCodePartnerAndBarcodeIgnoreCase(partner, barcode).orElseThrow(
                        () -> new EntityNotFoundException(Constants.BET_UNKNOWN)
                );

                // TODO: verification ticket
                betKeno.setStatus(BetStatus.PERDANT);
                betKenoRepo.save(betKeno);

                yield betKeno;
            }
            default -> throw new IllegalStateException(Constants.GAME_NOT_FOUND);
        };


    }

    @Override
    public Collection<Object> listAllGameOdds(String game) {
        return pari.getListPari().get(game).values();
    }
}
