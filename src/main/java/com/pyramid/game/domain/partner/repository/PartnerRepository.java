package com.pyramid.game.domain.partner.repository;

import com.pyramid.game.domain.partner.model.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    Page<Partner> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Optional<Partner> findPartnerByCodePartner(String code);
    Optional<Partner> findPartnerByDesignation(String designation);
}
