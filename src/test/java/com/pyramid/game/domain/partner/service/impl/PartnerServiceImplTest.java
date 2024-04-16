package com.pyramid.game.domain.partner.service.impl;

import com.pyramid.game.domain.partner.mapper.PartnerMapper;
import com.pyramid.game.domain.partner.model.Parameters;
import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.partner.model.enums.PartnerStatus;
import com.pyramid.game.domain.partner.repository.ParametersRepository;
import com.pyramid.game.domain.partner.repository.PartnerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    private Long partnerId;
    private String code;
    private Parameters parameters;


    @BeforeEach
    void setUp() {

        partnerId = 1L;
        code = "OLBXX1XNBO8RAM3CIBBM1713202891511";
        partner = new Partner();
        partner.setId(partnerId);
        partner.setStatus(PartnerStatus.ACTIVE);
        partner.setDesignation("PREMIUM");
        partner.setLocalisation("AFR-CEN");

        parameters = new Parameters();
        parameters.setPartner(partner);
        parameters.setCreatedAt(LocalDateTime.now());

    }

    @Test
    void shouldCreatePartner() {

        partner.setCodePartner(code);
        when(partnerRepository.save(any(Partner.class))).thenReturn(partner);
        when(parametersRepository.save(any(Parameters.class))).thenReturn(parameters);

        final var underTest = partnerService.createPartner(partner);

        assertNotNull(partner.getCodePartner());
        assertEquals(partner, underTest);
        verify(parametersRepository, times(1)).save(any(Parameters.class));
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
    void shouldResearchPartnerById() {

        when(partnerRepository.findById(anyLong())).thenReturn(Optional.of(partner));

        final var underTest = partnerService.researchPartner(partnerId);

        assertEquals(partner, underTest);
        verify(partnerRepository, times(1)).findById(anyLong());
    }

    @Test
    void shouldThrowsExceptionResearchPartnerById() {

        when(partnerRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> partnerService.researchPartner(partnerId));

        verify(partnerRepository, times(1)).findById(anyLong());
    }

    @Test
    void shouldResearchPartnerCode() {

        when(partnerRepository.findPartnerByCodePartner(anyString())).thenReturn(Optional.of(partner));

        final var underTest = partnerService.researchPartnerCode(code);

        assertEquals(partner, underTest);
        verify(partnerRepository, times(1)).findPartnerByCodePartner(anyString());
    }

    @Test
    void shouldThrowExceptionResearchPartnerCodeNotExist() {

        when(partnerRepository.findPartnerByCodePartner(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> partnerService.researchPartnerCode(code));

        verify(partnerRepository, times(1)).findPartnerByCodePartner(anyString());
    }

    @Test
    void shouldListAllPartners() {

        List<Partner> partnerList = List.of(partner);
        when(partnerRepository.findAll()).thenReturn(partnerList);

        final var underTest = partnerService.listAllPartners();

        assertEquals(underTest, partnerList);

        verify(partnerRepository, times(1)).findAll();
    }

    @Test
    void getPartnerPaginated() {
    }

}