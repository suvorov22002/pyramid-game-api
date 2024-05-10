package com.pyramid.game.domain.partner.service.impl;

import com.pyramid.game.core.utils.Constants;
import com.pyramid.game.domain.partner.mapper.PartnerMapper;
import com.pyramid.game.domain.partner.model.Parameters;
import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.partner.model.enums.PartnerStatus;
import com.pyramid.game.domain.partner.repository.ParametersRepository;
import com.pyramid.game.domain.partner.repository.PartnerRepository;
import com.pyramid.game.domain.partner.service.PartnerService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/04/2024
 * Time: 09:11
 * Project Name: pyramid-game-api
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepo;
    private final ParametersRepository parametersRepository;
    private final PartnerMapper partnerMapper;

    @Override
    public Partner createPartner(Partner partner) {

        partnerRepo.findPartnerByDesignation(partner.getDesignation()).ifPresent(
                p -> {
                    throw new EntityExistsException(Constants.PARTNER_ALREADY_EXIST);
                }
        );

        partner.setCodePartner("P" + Constants.generateCode());
        partner.setCreatedAt(LocalDateTime.now());
        Parameters parameters = new Parameters();

        Partner savePartner = partnerRepo.save(partner);
        parameters.setPartner(savePartner);
        parameters.setCreatedAt(LocalDateTime.now());
        parameters = parametersRepository.save(parameters);
        savePartner.setParameters(parameters);

        return savePartner;
    }

    @Override
    public void deletePartner(Long id) {

    }

    @Override
    public void enrollPartner(Partner partner) {

    }

    @Override
    public Partner updatePartnerStatus(Long id, String status) {
        Partner partner = researchPartner(id);
        String enumString = "TRUE".equalsIgnoreCase(status) ? "ACTIVE" : "INACTIVE";
        partner.setStatus(PartnerStatus.valueOf(enumString));
        return partnerRepo.save(partner);
    }

    @Override
    public Partner updatePartner(Long id, Partner partner) {
        Partner existingPartner = researchPartner(id);
        Partner updatedPartner = partnerMapper.update(partner, existingPartner);
        //updatedPartner.setUpdatedAt(LocalDateTime.now());
        return partnerRepo.save(updatedPartner);
    }

    @Override
    public Partner researchPartner(Long id) {
        return partnerRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Constants.PARTNER_NOT_FOUND)
        );
    }

    @Override
    public Partner researchPartnerCode(String code) {
        return partnerRepo.findPartnerByCodePartner(code).orElseThrow(
                () -> new EntityNotFoundException(Constants.PARTNER_NOT_FOUND)
        );
    }

    @Override
    public List<Partner> listAllPartners() {
        return partnerRepo.findAll();
    }

    @Override
    public Page<Partner> getPartnerPaginated(Pageable pageable) {
        return partnerRepo.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public Partner researchPartnerDesignation(String designation) {
        return partnerRepo.findPartnerByDesignation(designation).orElseThrow(
                () -> new EntityNotFoundException(Constants.PARTNER_NOT_FOUND)
        );

    }
}
