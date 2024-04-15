package com.pyramid.game.domain.partner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pyramid.game.domain.partner.mapper.PartnerMapper;
import com.pyramid.game.domain.partner.service.PartnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(PartnerController.class)
class PartnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PartnerService partnerService;

    @MockBean
    private PartnerMapper partnerMapper;

    @Test
    void subscribePartner() {
    }
}