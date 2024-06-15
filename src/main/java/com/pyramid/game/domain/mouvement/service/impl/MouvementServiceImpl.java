package com.pyramid.game.domain.mouvement.service.impl;

import com.pyramid.game.core.utils.Constants;
import com.pyramid.game.domain.mouvement.mapper.MouvementMapper;
import com.pyramid.game.domain.mouvement.model.Mouvement;
import com.pyramid.game.domain.mouvement.model.enums.Operation;
import com.pyramid.game.domain.mouvement.repository.MouvementRepository;
import com.pyramid.game.domain.mouvement.service.MouvementService;
import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.partner.repository.PartnerRepository;
import com.pyramid.game.domain.users.model.AppUser;
import com.pyramid.game.domain.users.repository.AppUserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by Suvorov Vassilievitch
 * Date: 14/06/2024
 * Time: 20:21
 * Project Name: pyramid-game-api
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MouvementServiceImpl implements MouvementService {
    
    private final MouvementRepository mouvementRepo;
    private final AppUserRepository appUserRepo;
    private final MouvementMapper mouvementMapper;
    private final PartnerRepository partnerRepo;
    
    
    @Override
    public synchronized Mouvement createMouvement(Mouvement mouvement) {
        
        AppUser appUser = appUserRepo.findAppUserByPartnerAndLogin(mouvement.getAppUser().getPartner(), mouvement.getAppUser().getLogin())
                .orElseThrow(
                        () -> new EntityNotFoundException(Constants.USER_NOT_FOUND)
                );
        
        // Mise à jour de la balance du user
        var balance = appUser.getBalance() == null ? 0d : appUser.getBalance();
        var operation = mouvement.getOperation();
        if(operation == Operation.CREDIT_CASHIER) {
            appUser.setBalance(balance + mouvement.getCredit());
            appUserRepo.save(appUser);
        }
        else if(operation == Operation.DEBIT_CASHIER) {
            appUser.setBalance(balance - mouvement.getDebit());
            appUserRepo.save(appUser);
        }

        List<Mouvement> mouvements = selectMouvement(appUser, 0);
        Optional<Mouvement> archMouvement = mouvements.stream().findFirst();

        if(archMouvement.isPresent()) {

            Mouvement movement = archMouvement.get();
            var archBalance = movement.getBalance();
            movement.setArchive(1);

            if(operation == Operation.CREDIT_CASHIER) {
                mouvement.setBalance(archBalance + mouvement.getCredit());
            }
            else if(operation == Operation.DEBIT_CASHIER) {
                mouvement.setBalance(archBalance - mouvement.getDebit());
            }

            mouvementRepo.save(movement);
        }
        else{

            if(operation == Operation.CREDIT_CASHIER) {
                mouvement.setBalance(mouvement.getCredit());
            }
            else if(operation == Operation.DEBIT_CASHIER) {
                mouvement.setBalance((-1)*mouvement.getDebit());
            }
        }
        mouvement.setCreatedAt(LocalDateTime.now());
        return mouvementRepo.save(mouvement);

    }

    @Override
    public Mouvement researchMouvement(Long id) {
        return mouvementRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Constants.PARTNER_NOT_FOUND)
        );
    }

    @Override
    public List<Mouvement> retrieveAllUserMouvement(String login, String part) {

        var partner = partnerRepo.findPartnerByCodePartner(part).orElseThrow(
                () -> new EntityNotFoundException(Constants.PARTNER_NOT_FOUND)
        );

        var cashier = appUserRepo.findAppUserByPartnerAndLogin(partner, login).orElseThrow(
                () -> new EntityNotFoundException(Constants.USER_NOT_FOUND)
        );

        return mouvementRepo.findByAppUserOrderByCreatedAtDesc(cashier);
    }

    @Override
    public List<Mouvement> retrieveAllMouvement() {
        return mouvementRepo.findAll();
    }

    @Override
    public List<Mouvement> selectMouvement(AppUser user, Integer archive) {
        return mouvementRepo.findByAppUserAndArchiveOrderByCreatedAtDesc(user, archive);
    }

    @Override
    public Mouvement updateMouvement(Long id, Mouvement mouvement) {
        Mouvement existingMouvement = researchMouvement(id);
        Mouvement updatedMouvement = mouvementMapper.update(mouvement, existingMouvement);
        return mouvementRepo.save(updatedMouvement);
    }

    @Override
    public Mouvement updateMouvementArchive(Long id, Integer archive) {
        Mouvement existingMouvement = researchMouvement(id);
        existingMouvement.setArchive(archive);
        return mouvementRepo.save(existingMouvement);
    }

    @Override
    public void deleteMouvement(Long id) {
        Mouvement existingMouvement = researchMouvement(id);
        mouvementRepo.delete(existingMouvement);
    }
}
