package com.pyramid.game.domain.salle.repository;

import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.salle.model.Salle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Suvorov Vassilievitch
 * Date: 21/04/2024
 * Time: 11:33
 * Project Name: pyramid-game-api
 */
@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {

    Page<Salle> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<Salle> findSalleByPartnerOrderByCreatedAtDesc(Partner partner, Pageable pageable);
    List<Salle> findSalleByCodeSalleLikeIgnoreCase(String codeSalle);
    Optional<Salle> findSalleByPartnerAndCodeSalle(Partner partner, String codeSalle);
    Optional<Salle> findSalleByPartnerAndDesignation(Partner partner, String designation);
    Optional<Salle> findSalleByCodeSalle(String codeSalle);
    List<Salle> findSalleByPartner(Partner partner);
}
