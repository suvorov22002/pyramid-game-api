package com.pyramid.game.domain.shift.service;

import com.pyramid.game.domain.bet.model.BetKeno;
import com.pyramid.game.domain.shift.model.ShiftKeno;

import java.time.LocalDateTime;
import java.util.List;

public interface ShiftKenoService {
    ShiftKeno extractStactistics(String login, String partner, String salle, LocalDateTime start, LocalDateTime end);
}
