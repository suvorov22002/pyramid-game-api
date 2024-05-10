package com.pyramid.game.domain.partner.service.impl;

import com.pyramid.game.core.utils.Constants;
import com.pyramid.game.domain.partner.mapper.EnrollmentMapper;
import com.pyramid.game.domain.partner.model.Enrollment;
import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.partner.model.enums.EnrollStatus;
import com.pyramid.game.domain.partner.repository.EnrollmentRepository;
import com.pyramid.game.domain.partner.repository.PartnerRepository;
import com.pyramid.game.domain.partner.service.EnrollmentService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Suvorov Vassilievitch
 * Date: 20/04/2024
 * Time: 20:18
 * Project Name: pyramid-game-api
 */
@Service
@Transactional
@RequiredArgsConstructor
public class EnrollmentServiceImplement implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepo;
    private final EnrollmentMapper enrollmentMapper;
    private final PartnerRepository partnerRepo;

    @Override
    public Enrollment subscribre(Enrollment enrollment) {

        enrollmentRepo
                .findEnrollmentByPartnerAndGame(enrollment.getPartner(), enrollment.getGame())
                .ifPresent(
                        enroll -> { throw new EntityExistsException(Constants.ENROLLMENT_GAME_EXIST); }
                );
        enrollment.setEnrollAt(LocalDateTime.now());
        enrollment.setCreatedAt(LocalDateTime.now());
        return enrollmentRepo.save(enrollment);
    }

    @Override
    public List<Enrollment> subscribreAll(List<Enrollment> enrollments) {
        List<Enrollment> newEnrollments = enrollments.stream().peek(e -> {
            e.setEnrollAt(LocalDateTime.now());
            e.setCreatedAt(LocalDateTime.now());
        }).toList();

        return enrollmentRepo.saveAll(newEnrollments);
    }

    @Override
    public List<Enrollment> allEnrollments() {
        return enrollmentRepo.findAll();
    }

    @Override
    public void unsubscribe(Long id) {
        Enrollment enrollment = enrollmentRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Constants.ENROLLMENT_NOT_FOUND)
        );
        enrollment.setDeregistrationAt(LocalDateTime.now());
        enrollment.setStatus(EnrollStatus.WITHDRAWN);
        enrollmentRepo.save(enrollment);
    }

    @Override
    public Enrollment updateEnrollment(Long id, Enrollment enrollment) {
        Enrollment existingEnroll = enrollmentRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Constants.ENROLLMENT_NOT_FOUND)
        );
        Enrollment enrollmentUpdate = enrollmentMapper.update(enrollment,existingEnroll);
        return enrollmentRepo.save(enrollmentUpdate);
    }

    @Override
    public List<Enrollment> selectEnrollPartner(String partner) {
        Partner part = partnerRepo.findPartnerByDesignation(partner).orElse(null);
        return enrollmentRepo.findEnrollmentByPartner(part);
    }
}
