package com.pyramid.game.domain.bet.service;

import com.pyramid.game.domain.bet.model.Bet;

import java.util.Collection;

public interface BetService {

    Bet createBet(Bet bet);
    Object selectBet(String partner, String barcode);
    Collection<Object> listAllGameOdds(String game);
}
