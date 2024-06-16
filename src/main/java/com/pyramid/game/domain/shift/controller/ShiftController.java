package com.pyramid.game.domain.shift.controller;

import com.pyramid.game.domain.salle.dto.SalleResponse;
import com.pyramid.game.domain.shift.dto.ShiftDto;
import com.pyramid.game.domain.shift.mapper.ShiftMapper;
import com.pyramid.game.domain.shift.service.ShiftService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/06/2024
 * Time: 19:17
 * Project Name: pyramid-game-api
 */
@RestController
@RequestMapping("/api/v1/shifts")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Shift Management")
public class ShiftController {

   private final ShiftService shiftService;
   private final ShiftMapper mapper;

    @PostMapping
    ResponseEntity<ShiftDto> createNewShift(@Valid @RequestBody ShiftDto shiftDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toDto(shiftService.createShift(mapper.toEntity(shiftDto))));
    }

    @GetMapping("/cashier/{user}/partner/{partner}")
    ResponseEntity<List<ShiftDto>> listAllShift(@PathVariable String user, @PathVariable String partner) {
        return ResponseEntity
                .ok(mapper.toDtoList(shiftService.allShift(user, partner)));
    }

    @GetMapping("/cashier/{user}/partner/{partner}/dated/{start}/{end}")
    ResponseEntity<List<ShiftDto>> listAllShiftDated(@PathVariable String user, @PathVariable String partner
            , @PathVariable LocalDateTime start, @PathVariable LocalDateTime end) {
        return ResponseEntity
                .ok(mapper.toDtoList(shiftService.allShiftDated(user, partner, start, end)));
    }

    @PostMapping("/open")
    ResponseEntity<ShiftDto> selectShift(@Valid @RequestBody ShiftDto shiftDto) {
        return ResponseEntity
                .ok(mapper.toDto(shiftService.searchShift(mapper.toEntity(shiftDto))));
    }

    @PostMapping("/summarize")
    ResponseEntity<ShiftDto> summarizeShift(@Valid @RequestBody ShiftDto shiftDto) {
        return ResponseEntity
                .ok(mapper.toDto(shiftService.summarizeShift(mapper.toEntity(shiftDto))));
    }

    @PutMapping
    ResponseEntity<ShiftDto> closeShift(@Valid @RequestBody ShiftDto shiftDto) {
        return ResponseEntity
                .ok(mapper.toDto(shiftService.updateShift(mapper.toEntity(shiftDto))));
    }

}
