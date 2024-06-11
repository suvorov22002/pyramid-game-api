package com.pyramid.game.domain.salle.service;

import com.pyramid.game.domain.salle.model.Salle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;


public interface SalleService {
    
    Salle createRoom(Salle salle);
    Page<Salle> listAllRoomsPaginated(Pageable pageable);
    Page<Salle> listAllRoomsPaginated(Pageable pageable, String partnerCode);
    Salle searchRoom(Long id);
    List<Salle> searchRoomPartnerCode(String partnerCode);

    List<Salle> searchRoomCode(String code);
    Salle searchPartnerRoomCode(String partnerCode, String code);
    Salle searchPartnerDesignation(String partnerCode, String designation);
    @Modifying
    Salle updateSalleInfo(Long id, Salle Salle);
    void deleteSalle(Long id);
    @Modifying
    Salle suspendSalle(Salle user);
    @Modifying
    Salle updateSalleStatus(Long id, String status);
}
