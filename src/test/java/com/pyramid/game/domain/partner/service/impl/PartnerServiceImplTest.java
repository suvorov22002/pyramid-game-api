package com.pyramid.game.domain.partner.service.impl;

import com.pyramid.game.domain.partner.mapper.PartnerMapper;
import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.partner.model.enums.PartnerStatus;
import com.pyramid.game.domain.partner.repository.ParametersRepository;
import com.pyramid.game.domain.partner.repository.PartnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PartnerServiceImplTest {

    @InjectMocks
    PartnerServiceImpl partnerService;

    @Mock
    PartnerRepository partnerRepository;

    @Mock
    ParametersRepository parametersRepository;

    @Mock
    PartnerMapper partnerMapper;
    private Partner partner;


    @BeforeEach
    void setUp() {

        partner = new Partner();
        partner.setId(1L);
        partner.setCodePartner("REYBDO654378293");
        partner.setStatus(PartnerStatus.ACTIVE);
        partner.setDesignation("PREMIUM");
        partner.setLocalisation("AFR-CEN");

    }

    @Test
    void shouldCreatePartner() {
        when(partnerRepository.save(any(Partner.class))).thenReturn(partner);

        final var underTest = partnerService.createPartner(partner);

        assertEquals(partner, underTest);
        verify(partnerRepository, times(1)).save(any(Partner.class));

    }

    @Test
    void deletePartner() {
    }

    @Test
    void enrollPartner() {
    }

    @Test
    void updatePartnerStatus() {
    }

    @Test
    void updatePartner() {
    }

    @Test
    void researchPartner() {
    }

    @Test
    void researchPartnerCode() {
    }

    @Test
    void listAllPartners() {
    }

    @Test
    void getPartnerPaginated() {
    }
}