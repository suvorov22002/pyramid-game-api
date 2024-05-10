package com.pyramid.game.domain.salle.service.impl;

import com.pyramid.game.core.utils.Constants;
import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.partner.model.enums.PartnerStatus;
import com.pyramid.game.domain.partner.repository.PartnerRepository;
import com.pyramid.game.domain.salle.mapper.SalleMapper;
import com.pyramid.game.domain.salle.model.Salle;
import com.pyramid.game.domain.salle.model.enums.SalleStatus;
import com.pyramid.game.domain.salle.repository.SalleRepository;
import com.pyramid.game.domain.salle.service.SalleService;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 21/04/2024
 * Time: 11:31
 * Project Name: pyramid-game-api
 */
@Service
@Transactional
@RequiredArgsConstructor
public class SalleServiceImplement implements SalleService {

    private final SalleRepository salleRepo;
    private final PartnerRepository partnerRepo;
    private final SalleMapper salleMapper;

    @Override
    public Salle createRoom(Salle salle) {

        salle.setCreatedAt(LocalDateTime.now());
        salle.setCodeSalle("R" + Constants.generateCode());
        return salleRepo.save(salle);

    }

    @Override
    public Page<Salle> listAllRoomsPaginated(Pageable pageable) {
        return salleRepo.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public Page<Salle> listAllRoomsPaginated(Pageable pageable, String partnerCode) {
        Partner partner = partnerRepo.findPartnerByCodePartner(partnerCode)
                .orElseThrow(() -> new EntityNotFoundException(Constants.PARTNER_NOT_FOUND));
        return salleRepo.findSalleByPartnerOrderByCreatedAtDesc(partner, pageable);
    }

    @Override
    public Salle searchRoom(Long id) {
        return salleRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Constants.ROOM_NOT_FOUND)
        );
    }

    @Override
    public List<Salle> searchRoomPartnerCode(String partnerCode) {
        Partner partner = partnerRepo.findPartnerByCodePartner(partnerCode)
                .orElseThrow(() -> new EntityNotFoundException(Constants.PARTNER_NOT_FOUND));
        return salleRepo.findSalleByPartner(partner);
    }

    @Override
    public List<Salle> searchRoomCode(String code) {
        return salleRepo.findSalleByCodeSalleLikeIgnoreCase(code);
    }

    @Override
    public Salle searchPartnerRoomCode(String partnerCode, String code) {
        Partner partner = partnerRepo.findPartnerByCodePartner(partnerCode)
                .orElseThrow(() -> new EntityNotFoundException(Constants.PARTNER_NOT_FOUND));

        return salleRepo.findSalleByPartnerAndCodeSalle(partner, code)
                .orElseThrow(
                        () -> new EntityNotFoundException(Constants.ROOM_NOT_FOUND)
                );

    }

    @Override
    public Salle updateSalleInfo(Long id, Salle salle) {
        Salle existingRoom = searchRoom(id);
        Salle updatedRoom = salleMapper.update(salle, existingRoom);
        return salleRepo.save(updatedRoom);
    }

    @Override
    public void deleteSalle(Long id) {
        Salle existingRoom = searchRoom(id);
        salleRepo.delete(existingRoom);
    }

    @Override
    public Salle suspendSalle(Salle user) {
        Salle existingRoom = searchRoom(user.getId());
        existingRoom.setStatus(SalleStatus.INACTIVE);
        return salleRepo.save(existingRoom);
    }

    @Override
    public Salle updateSalleStatus(Long id, String status) {
        Salle existingRoom = searchRoom(id);
        String enumString = "TRUE".equalsIgnoreCase(status) ? "ACTIVE" : "INACTIVE";
        existingRoom.setStatus(SalleStatus.valueOf(enumString));
        return salleRepo.save(existingRoom);
    }


}
