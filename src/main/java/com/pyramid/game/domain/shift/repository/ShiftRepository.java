package com.pyramid.game.domain.shift.repository;

import com.pyramid.game.domain.shift.model.Shift;
import com.pyramid.game.domain.shift.model.enums.ShiftStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {

    List<Shift> findByLoginIgnoreCaseAndPartnerIgnoreCaseAndSalleIgnoreCaseAndShiftStatusOrderByCreatedAtDesc
            (String user, String partner, String salle, ShiftStatus shiftStatus);
    List<Shift> findByLoginIgnoreCaseAndPartnerIgnoreCaseOrderByCreatedAtDesc(String user, String partner);
    List<Shift> findByLoginIgnoreCaseAndPartnerIgnoreCaseAndCreatedAtBetweenOrderByCreatedAtDesc(String user, String partner, LocalDateTime start, LocalDateTime end);

}
