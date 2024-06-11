package com.pyramid.game.domain.withdraw.service;

import com.pyramid.game.domain.withdraw.model.Versement;

import java.time.LocalDateTime;
import java.util.List;

public interface VersementService {

    List<Versement> listAllUserDraw(String login, String partner);
    List<Versement> listAllUserDrawDated(String login, String partner, LocalDateTime start, LocalDateTime end);
    List<Versement> listAllUserDrawRoomDated(String login, String partner, String salle, LocalDateTime start, LocalDateTime end);
    List<Versement> listAllPartnerDrawDated(String partner, LocalDateTime start, LocalDateTime end);
    List<Versement> listAllPartnerDrawRoomDated(String salle, String partner, LocalDateTime start, LocalDateTime end);
    Versement selectVersement(Long numeroTicket);
    Versement createWithdrawal(Versement versement);
}
