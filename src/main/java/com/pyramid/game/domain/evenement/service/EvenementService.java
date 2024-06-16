package com.pyramid.game.domain.evenement.service;

import com.pyramid.game.domain.evenement.model.Evenement;
import com.pyramid.game.domain.jeu.model.Game;
import com.pyramid.game.domain.salle.model.Salle;

import java.util.List;

public interface EvenementService {

    Evenement addEvent(Evenement evenement);
    Evenement updateEvent(Evenement evenement);
    List<Evenement> listAllEventPartnerGame(String salleCode, String gameCode);
    Evenement updateEventMontantBonus(Evenement evenement, Double montant);
}
