package com.pyramid.game.domain.slip.service.impl;

import com.pyramid.game.domain.bet.model.BetKeno;
import com.pyramid.game.domain.slip.model.SlipKeno;
import com.pyramid.game.domain.slip.repository.SlipKenoRepository;
import com.pyramid.game.domain.slip.service.SlipKenoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 11/05/2024
 * Time: 23:13
 * Project Name: pyramid-game-api
 */
@Service
@Transactional
@RequiredArgsConstructor
public class SlipKenoServiceImpl implements SlipKenoService {

    private final SlipKenoRepository slipKenoRepo;

    @Override
    public SlipKeno createSlip(SlipKeno slipKeno) {

        slipKeno.setCreatedAt(LocalDateTime.now());
        return slipKenoRepo.save(slipKeno);
    }

    @Override
    public List<SlipKeno> createListSlip(List<SlipKeno> slipKenos) {

        for(SlipKeno slipKeno : slipKenos) {
            slipKeno.setCreatedAt(LocalDateTime.now());
        }

        return slipKenoRepo.saveAll(slipKenos);
    }

    @Override
    public List<SlipKeno> getAllBetKenoSlip(BetKeno betKeno) {
        return slipKenoRepo.findSlipKenoByBetKeno(betKeno);
    }

}
