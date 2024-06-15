package com.pyramid.game.domain.shift.repository;

import com.pyramid.game.domain.shift.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
}
