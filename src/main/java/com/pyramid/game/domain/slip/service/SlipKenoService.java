package com.pyramid.game.domain.slip.service;

import com.pyramid.game.domain.bet.model.BetKeno;
import com.pyramid.game.domain.slip.model.SlipKeno;

import java.util.List;

public interface SlipKenoService {

    SlipKeno createSlip(SlipKeno slipKeno);
    List<SlipKeno> createListSlip(List<SlipKeno> slipKenos);

    List<SlipKeno> getAllBetKenoSlip(BetKeno betKeno);

}
