package com.pyramid.game.domain.partner.controller;

import com.pyramid.game.core.pagination.PageDto;
import com.pyramid.game.core.pagination.PaginationUtils;
import com.pyramid.game.domain.partner.dto.PartnerRequest;
import com.pyramid.game.domain.partner.dto.PartnerResponse;
import com.pyramid.game.domain.partner.mapper.EnrollmentMapper;
import com.pyramid.game.domain.partner.mapper.PartnerMapper;
import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.partner.service.PartnerService;
import com.pyramid.game.domain.utils.RequestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/04/2024
 * Time: 14:40
 * Project Name: pyramid-game-api
 */
@RestController
@RequestMapping("/api/v1/partners")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Partner Management")
public class PartnerController {

    private final PartnerService partnerService;
    private final PartnerMapper mapper;
    private final EnrollmentMapper enrollmentMapper;

    @Operation(
            description = "Endpoint for partner creation",
            summary = "This is a summary for management post endpoint"
    )
    @PostMapping
    ResponseEntity<PartnerResponse> subscribePartner(@Valid @RequestBody PartnerRequest partnerRequest) {

        Partner partner = mapper.toEntity(partnerRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toDto(partnerService
                        .createPartner(partner)));
    }

    @GetMapping("/all")
    ResponseEntity<List<PartnerResponse>> listAllPartners() {
        return ResponseEntity.ok(
                mapper.toDtoList(partnerService.listAllPartners())
        );
    }

    @GetMapping
    ResponseEntity<PageDto<PartnerResponse>> listAllPartnersPaginated(Pageable pageable){
        return ResponseEntity.ok(
                PaginationUtils.convertEntityPageToDtoPage(partnerService.getPartnerPaginated(pageable), mapper::toDtoList)
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<PartnerResponse> searchPartnerId(@PathVariable Long id) {

        return ResponseEntity.ok(mapper.toDto(partnerService.researchPartner(id)));

    }

    @GetMapping("/code/{code}")
    ResponseEntity<PartnerResponse> searchPartnerCode(@PathVariable String code) {

        return ResponseEntity.ok(mapper.toDto(partnerService.researchPartnerCode(code)));

    }

    @PutMapping("/{id}/{status}")
    ResponseEntity<PartnerResponse> updatePartnerStatus(@PathVariable("id") Long id, @PathVariable("status") String status) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toDto(partnerService.updatePartnerStatus(id, status)));
    }

    @PutMapping("/{id}")
    ResponseEntity<PartnerResponse> updatePartner(@PathVariable Long id, @RequestBody PartnerRequest partnerRequest) {

        return ResponseEntity.ok(
                mapper.toDto(partnerService.updatePartner(id, mapper.toEntity(partnerRequest)))
        );
    }
}
