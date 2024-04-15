package com.pyramid.game.domain.partner.service;

import com.pyramid.game.domain.partner.dto.PartnerRequest;
import com.pyramid.game.domain.partner.model.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface PartnerService {

    Partner createPartner(Partner partner);
    void deletePartner(Long id);
    @Modifying
    void enrollPartner(Partner partner);
    @Modifying
    Partner updatePartnerStatus(Long id, String status);
    @Modifying
    Partner updatePartner(Long id, Partner partner);
    Partner researchPartner(Long id);
    Partner researchPartnerCode(String code);
    List<Partner> listAllPartners();
    Page<Partner> getPartnerPaginated(Pageable pageable);
}
