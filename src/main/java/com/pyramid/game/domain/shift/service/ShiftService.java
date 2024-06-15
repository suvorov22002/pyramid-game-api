package com.pyramid.game.domain.shift.service;


import com.pyramid.game.domain.shift.model.Shift;

import java.time.LocalDateTime;
import java.util.List;

public interface ShiftService {

    Shift createShift(String login, String partner, String salle, LocalDateTime start);
    List<Shift> allShift(String user, String partner);
    List<Shift> allShiftDated(String user, String partner, LocalDateTime start, LocalDateTime end);
    Shift updateShift(Long id, Shift shift);
    Shift searchShift(Long id);

}
