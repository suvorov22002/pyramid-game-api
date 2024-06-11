package com.pyramid.game.domain.bet.controller;

import com.pyramid.game.core.config.Pari;
import com.pyramid.game.core.pagination.PageDto;
import com.pyramid.game.core.pagination.PaginationUtils;
import com.pyramid.game.domain.bet.dto.BetKenoRequest;
import com.pyramid.game.domain.bet.dto.BetKenoResponse;
import com.pyramid.game.domain.bet.mapper.BetKenoMapper;
import com.pyramid.game.domain.bet.model.BetKeno;
import com.pyramid.game.domain.bet.service.BetKenoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Suvorov Vassilievitch
 * Date: 10/05/2024
 * Time: 22:24
 * Project Name: pyramid-game-api
 */
@RestController
@RequestMapping("/api/v1/bets")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Bet Keno Management")
public class BetKenoController {

    private final BetKenoService betService;
    private final BetKenoMapper mapper;
    private final Pari pari;

    @PostMapping("/betkeno")
    ResponseEntity<BetKenoResponse> placeBetKeno(@Valid @RequestBody BetKenoRequest betRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toDto(betService.createBet(mapper.toEntity(betRequest))));

    }

    @GetMapping("/all/partner/{partner}")
    ResponseEntity<List<BetKenoResponse>> listAllBetKeno(@PathVariable String partner){
        return ResponseEntity.ok(mapper.toDtoList(betService.listAllBetPartner(partner)));
    }

    @GetMapping("/partner/{partner}")
    ResponseEntity<PageDto<BetKenoResponse>> listBetKenoPaginated(@PathVariable String partner, Pageable pageable){
        return ResponseEntity.ok(
                PaginationUtils.convertEntityPageToDtoPage(betService.getBetPartnerPaginated(partner, pageable), mapper::toDtoList)
        );
    }

    @GetMapping("/all/partner/{partner}/room/{name}")
    ResponseEntity<List<BetKenoResponse>> listAllBetKenoRoom(@PathVariable String partner, @PathVariable String name){
        return ResponseEntity.ok(mapper.toDtoList(betService.listAllBetPartnerRoom(partner, name)));
    }

    @GetMapping("/partner/{partner}/room/{name}")
    ResponseEntity<PageDto<BetKenoResponse>> listBetKenoRoomPaginated(@PathVariable String partner,
                                                                      @PathVariable String name, Pageable pageable){
        return ResponseEntity.ok(
                PaginationUtils
                        .convertEntityPageToDtoPage(betService.getBetPartnerRoomPaginated(partner, name, pageable),
                                mapper::toDtoList)
        );
    }

    @GetMapping("/partner/{partner}/barcode/{barcode}")
    ResponseEntity<BetKenoResponse> searchBetKenoPartnerBarcode(@PathVariable String partner, @PathVariable Long barcode) {
        return ResponseEntity.ok(
                mapper.toDto(betService.searchBetPartnerBarcode(partner, barcode))
        );
    }

    @GetMapping("/all/cashier/{login}")
    ResponseEntity<List<BetKenoResponse>> listAllCashierBetKeno(@PathVariable String login) {
        return ResponseEntity.ok(
                mapper.toDtoList(betService.searchBetCashier(login))
        );
    }

    @GetMapping("/cashier/{login}")
    ResponseEntity<PageDto<BetKenoResponse>> listAllCashierBetKenoPaginated(@PathVariable String login, Pageable pageable) {
        return ResponseEntity.ok(
                PaginationUtils.convertEntityPageToDtoPage(betService.searchBetCashierPaginated(login, pageable), mapper::toDtoList)
        );
    }

    @GetMapping("/{numero}")
    ResponseEntity<BetKenoResponse> selectBetKeno(@PathVariable Long numero) {
        return ResponseEntity.ok(
                mapper.toDto(betService.searchBetNumero(numero))
        );
    }

    @DeleteMapping("/{numero}")
    void deleteBet(@PathVariable Long numero){
        betService.deleteBetKeno(numero);

    }

    @PutMapping("/archive/{numero}")
    ResponseEntity<BetKenoResponse> updateArchive(@PathVariable Long numero) {
        return ResponseEntity.ok(
                mapper.toDto(betService.updateBetKenoArchive(numero))
        );
    }

    @PutMapping("/all/archive")
    ResponseEntity<List<BetKenoResponse>> updateListArchive(@RequestBody List<BetKenoRequest> betKenoRequests) {

        List<BetKeno> betKenoList = betKenoRequests.stream().map(mapper::toEntity).collect(Collectors.toList());

        return ResponseEntity.ok(
                mapper.toDtoList(betService.updateAllBetKenoArchive(betKenoList))
        );
    }

    @PutMapping("gain/{numero}/{montant}")
    ResponseEntity<BetKenoResponse> updateGain(@PathVariable Long numero, @PathVariable Double montant) {
        return ResponseEntity.ok(
                mapper.toDto(betService.updateBetKenoMontantGain(numero, montant))
        );
    }

    @PutMapping("/status/{id}/{status}")
    ResponseEntity<BetKenoResponse> updateStatus(@PathVariable Long id, @PathVariable String status) {
        return ResponseEntity.ok(
                mapper.toDto(betService.updateStatus(id, status))
        );
    }

    @GetMapping("/events/odds/{game}")
    ResponseEntity<Collection<Object>> listEventOdds(@PathVariable String game){
        return ResponseEntity.ok(betService.listAllGameOdds(game.toLowerCase()));
    }
}
