package com.pyramid.game.domain.salle.controller;

/**
 * Created by Suvorov Vassilievitch
 * Date: 21/04/2024
 * Time: 11:59
 * Project Name: pyramid-game-api
 */

import com.pyramid.game.core.pagination.PageDto;
import com.pyramid.game.core.pagination.PaginationUtils;
import com.pyramid.game.domain.salle.dto.SalleRequest;
import com.pyramid.game.domain.salle.dto.SalleResponse;
import com.pyramid.game.domain.salle.mapper.SalleMapper;
import com.pyramid.game.domain.salle.service.SalleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Salle Management")
public class SalleController {

    private final SalleService salleService;
    private final SalleMapper mapper;

    @PostMapping
    ResponseEntity<SalleResponse> createNewRoom(@Valid @RequestBody SalleRequest salleRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toDto(salleService.createRoom(mapper.toEntity(salleRequest))));
    }

    @GetMapping
    ResponseEntity<PageDto<SalleResponse>> listAllRooms(Pageable pageable) {
        return ResponseEntity.ok(
                PaginationUtils.convertEntityPageToDtoPage(salleService.listAllRoomsPaginated(pageable), mapper::toDtoList)
        );
    }

    @GetMapping("/all/partner/{partnercode}")
    ResponseEntity<PageDto<SalleResponse>> listAllRoomsPartnerPaginated(Pageable pageable,
                                                                          @PathVariable("partnercode") String partnercode){
        return ResponseEntity.ok(
                PaginationUtils.convertEntityPageToDtoPage(salleService.listAllRoomsPaginated(pageable, partnercode), mapper::toDtoList)
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<SalleResponse> selectRoomId(@PathVariable Long id) {
        return ResponseEntity
                .ok(mapper.toDto(salleService.searchRoom(id)));
    }

    @GetMapping("/login/{code}")
    ResponseEntity<List<SalleResponse>> listAllRoomCode(@PathVariable String code) {
        return ResponseEntity
                .ok(mapper.toDtoList(salleService.searchRoomCode(code)));
    }

    @GetMapping("/partner/{code}")
    ResponseEntity<List<SalleResponse>> listAllRoomPartnerCode(@PathVariable String code) {
        return ResponseEntity
                .ok(mapper.toDtoList(salleService.searchRoomPartnerCode(code)));
    }

    @GetMapping("/partner/{partnerCode}/{code}")
    ResponseEntity<SalleResponse> selectPartnerRoomCode(@PathVariable("partnerCode") String partnerCode,
                                                           @PathVariable("code") String code) {
        return ResponseEntity
                .ok(mapper.toDto(salleService.searchPartnerRoomCode(partnerCode, code)));
    }

    @GetMapping("/partner/{partnerCode}/designation/{name}")
    ResponseEntity<SalleResponse> selectPartnerDesignation(@PathVariable("partnerCode") String partnerCode,
                                                         @PathVariable("name") String name) {
        return ResponseEntity
                .ok(mapper.toDto(salleService.searchPartnerDesignation(partnerCode, name)));
    }

    @PutMapping("/{id}")
    ResponseEntity<SalleResponse> updateUserInfo(@Valid @RequestBody SalleRequest salleRequest, @PathVariable Long id) {
        return ResponseEntity
                .ok(mapper.toDto(salleService.updateSalleInfo(id, mapper.toEntity(salleRequest))));
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        salleService.deleteSalle(id);
    }

    @PutMapping("/{id}/{status}")
    ResponseEntity<SalleResponse> changeRoomStatus(@PathVariable("id") Long id, @PathVariable("status") String status) {
        return ResponseEntity
                .ok(mapper.toDto(salleService.updateSalleStatus(id, status)));

    }

}
