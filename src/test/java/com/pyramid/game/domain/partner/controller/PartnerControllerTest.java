package com.pyramid.game.domain.partner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pyramid.game.domain.partner.dto.PartnerRequest;
import com.pyramid.game.domain.partner.dto.PartnerResponse;
import com.pyramid.game.domain.partner.mapper.EnrollmentMapper;
import com.pyramid.game.domain.partner.mapper.PartnerMapper;
import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.partner.service.PartnerService;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(InstancioExtension.class)
@WebMvcTest(PartnerController.class)
@AutoConfigureMockMvc(addFilters = false)
class PartnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PartnerService partnerService;

    @MockBean
    private PartnerMapper partnerMapper;

    @MockBean
    private EnrollmentMapper enrollmentMapper;

    private Partner partner;
    private PartnerRequest partnerRequest;
    private PartnerResponse partnerResponse;

    @BeforeEach
    void setup() {
        partner = Instancio.create(Partner.class);
        partner.setCodePartner("OLBXX1XNBO8RAM3CIBBM1713202891511");
        partner.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        partnerRequest = Instancio.create(PartnerRequest.class);
        partnerResponse = Instancio.create(PartnerResponse.class);
        partnerResponse.setGames(Collections.emptyList());
        partnerResponse.setCodePartner("OLBXX1XNBO8RAM3CIBBM1713202891511");


    }
    @Test
    void subscribePartner() throws Exception {
        when(partnerMapper.toEntity(any())).thenReturn(partner);
        when(partnerService.createPartner(partner)).thenReturn(partner);
        when(partnerMapper.toDto(partner)).thenReturn(partnerResponse);

        MockHttpServletResponse response = mockMvc.perform(post("/api/v1/partners")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(partnerRequest)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        assertEquals(objectMapper.writeValueAsString(partnerResponse), response.getContentAsString());

        verify(partnerService).createPartner(partner);
    }
}