package com.pyramid.game.domain.partner.controller;

import com.pyramid.game.domain.partner.dto.EnrollmentRequest;
import com.pyramid.game.domain.partner.dto.EnrollmentResponse;
import com.pyramid.game.domain.partner.mapper.EnrollmentMapper;
import com.pyramid.game.domain.partner.model.Enrollment;
import com.pyramid.game.domain.partner.service.EnrollmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Suvorov Vassilievitch
 * Date: 20/04/2024
 * Time: 21:38
 * Project Name: pyramid-game-api
 */
@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Subscription Management")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final EnrollmentMapper mapper;

    @PostMapping
    ResponseEntity<EnrollmentResponse> newSubscription(@Valid @RequestBody EnrollmentRequest request) {

        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(mapper.toDto(enrollmentService.subscribre(mapper.toEntity(request))));
    }

    @PostMapping("/all")
    ResponseEntity<List<EnrollmentResponse>> newAllSubscription(@Valid @RequestBody List<EnrollmentRequest> requests) {

        List<Enrollment> enrollmentList = requests.stream().map(mapper::toEntity).collect(Collectors.toList());
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(mapper.toDtoList(enrollmentService.subscribreAll(enrollmentList)));
    }

    @GetMapping
    ResponseEntity<List<EnrollmentResponse>> listAllEnroll(){
        return ResponseEntity
                .ok(mapper.toDtoList(enrollmentService.allEnrollments()));
    }

    @GetMapping("/partner/{partner}")
    ResponseEntity<List<EnrollmentResponse>> listAllPartnerEnroll(@PathVariable String partner){
        return ResponseEntity
                .ok(mapper.toDtoList(enrollmentService.selectEnrollPartner(partner)));
    }

    @DeleteMapping("/{id}")
    void unsubscribe(@PathVariable Long id) {
        enrollmentService.unsubscribe(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<EnrollmentResponse> updateEnrollment(@PathVariable("id") Long id, @RequestBody EnrollmentRequest enrollmentRequest) {
        return ResponseEntity
                .ok(mapper.toDto(enrollmentService.updateEnrollment(id, mapper.toEntity(enrollmentRequest))));
    }
}
