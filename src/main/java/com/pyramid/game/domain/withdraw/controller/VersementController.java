package com.pyramid.game.domain.withdraw.controller;

import com.pyramid.game.domain.bet.dto.BetKenoResponse;
import com.pyramid.game.domain.withdraw.dto.VersementResponse;
import com.pyramid.game.domain.withdraw.mapper.VersementMapper;
import com.pyramid.game.domain.withdraw.service.VersementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 11/06/2024
 * Time: 11:45
 * Project Name: pyramid-game-api
 */
@RestController
@RequestMapping("/api/v1/versements")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Withdrawal Management")
public class VersementController {

    private final VersementService versementService;
    private final VersementMapper mapper;

    @GetMapping("/cashier/{login}/{partner}")
    ResponseEntity<List<VersementResponse>> listAllUserWithDrawal(@PathVariable String login, @PathVariable String partner){
        return ResponseEntity.ok(mapper.toDtoList(versementService.listAllUserDraw(login, partner)));
    }

    @GetMapping("/cashier/{login}/{partner}/{start}/{end}")
    ResponseEntity<List<VersementResponse>> listAllUserWithDrawalDated(@PathVariable String login, @PathVariable String partner,
             @PathVariable LocalDateTime start, @PathVariable LocalDateTime end){
        return ResponseEntity.ok(mapper.toDtoList(versementService.listAllUserDrawDated(login, partner, start, end)));
    }

    @GetMapping("/cashier/{login}/{partner}/room/{room}/{start}/{end}")
    ResponseEntity<List<VersementResponse>> listAllUserWithDrawalRoomDated(@PathVariable String login, @PathVariable String partner
            , @PathVariable String room, @PathVariable LocalDateTime start, @PathVariable LocalDateTime end){
        return ResponseEntity.ok(mapper.toDtoList(versementService.listAllUserDrawRoomDated(login, partner, room, start, end)));
    }

    @GetMapping("/partner/{partner}/{start}/{end}")
    ResponseEntity<List<VersementResponse>> listAllUserWithDrawalDated(@PathVariable String partner
            , @PathVariable LocalDateTime start, @PathVariable LocalDateTime end){
        return ResponseEntity.ok(mapper.toDtoList(versementService.listAllPartnerDrawDated(partner, start, end)));
    }

    @GetMapping("/partner/{partner}/room/{room}/{start}/{end}")
    ResponseEntity<List<VersementResponse>> listAllPartnerWithDrawalRoomDated(@PathVariable String partner,@PathVariable String room
            , @PathVariable LocalDateTime start, @PathVariable LocalDateTime end){
        return ResponseEntity.ok(mapper.toDtoList(versementService.listAllPartnerDrawRoomDated(partner, room, start, end)));
    }

    @GetMapping("/{numero}")
    ResponseEntity<VersementResponse> searchVersement(@PathVariable Long numero) {
        return ResponseEntity.ok(
                mapper.toDto(versementService.selectVersement(numero))
        );
    }
}
