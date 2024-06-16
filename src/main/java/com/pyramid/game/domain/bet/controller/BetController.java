package com.pyramid.game.domain.bet.controller;

import com.pyramid.game.core.config.Pari;
import com.pyramid.game.domain.bet.dto.BetKenoResponse;
import com.pyramid.game.domain.bet.mapper.BetKenoMapper;
import com.pyramid.game.domain.bet.service.BetKenoService;
import com.pyramid.game.domain.bet.service.BetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Suvorov Vassilievitch
 * Date: 16/06/2024
 * Time: 16:29
 * Project Name: pyramid-game-api
 */
@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Payments Management")
public class BetController {

    private final BetService betService;
    private final BetKenoMapper mapper;
    private final Pari pari;

    @GetMapping("/partner/{partner}/barcode/{barcode}")
    ResponseEntity<Object> searchBetPartnerBarcode(@PathVariable String partner, @PathVariable String barcode) {
        return ResponseEntity.ok(
                betService.selectBet(partner, barcode)
        );
    }

    @GetMapping("/events/odds/{game}")
    ResponseEntity<Collection<Object>> listEventOdds(@PathVariable String game){
        return ResponseEntity.ok(betService.listAllGameOdds(game.toLowerCase()));
    }

}
