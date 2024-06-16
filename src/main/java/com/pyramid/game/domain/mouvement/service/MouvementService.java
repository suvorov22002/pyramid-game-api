package com.pyramid.game.domain.mouvement.service;

import com.pyramid.game.domain.mouvement.model.Mouvement;
import com.pyramid.game.domain.users.model.AppUser;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface MouvementService {

    Mouvement createMouvement(Mouvement mouvement);
    Mouvement researchMouvement(Long id);
    List<Mouvement> retrieveAllUserMouvement(String login, String partner);
    List<Mouvement> retrieveAllMouvement();
    List<Mouvement> selectMouvement(AppUser user, Integer archive);
    @Modifying
    Mouvement updateMouvement(Long id, Mouvement mouvement);
    @Modifying
    Mouvement updateMouvementArchive(Long id, Integer archive);
    void deleteMouvement(Long id);
}
