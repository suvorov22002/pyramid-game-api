package com.pyramid.game.domain.partner.repository;

import com.pyramid.game.domain.jeu.model.Game;
import com.pyramid.game.domain.partner.model.Enrollment;
import com.pyramid.game.domain.partner.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    Optional<Enrollment> findEnrollmentByPartnerAndGame(Partner partner, Game game);
}
