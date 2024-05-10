package com.pyramid.game.domain.partner.service;

import com.pyramid.game.domain.partner.model.Enrollment;
import com.pyramid.game.domain.partner.model.Partner;

import java.util.List;

public interface EnrollmentService {

    Enrollment subscribre(Enrollment enrollment);
    List<Enrollment> subscribreAll(List<Enrollment> enrollments);
    List<Enrollment> allEnrollments();
    void unsubscribe(Long id);
    Enrollment updateEnrollment(Long id, Enrollment enrollment);
    List<Enrollment> selectEnrollPartner(String partner);
}
