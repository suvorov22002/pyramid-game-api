package com.pyramid.game.domain.evenement.service;

import com.pyramid.game.domain.evenement.model.Evenement;

public interface EvenementService {

    Evenement addEvent(Evenement evenement);
    Evenement updateEvent(Evenement evenement);
}
