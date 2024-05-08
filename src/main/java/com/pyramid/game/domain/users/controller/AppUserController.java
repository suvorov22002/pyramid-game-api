package com.pyramid.game.domain.users.controller;

import com.pyramid.game.core.pagination.PageDto;
import com.pyramid.game.core.pagination.PaginationUtils;
import com.pyramid.game.domain.salle.dto.SalleResponse;
import com.pyramid.game.domain.users.dto.AppUserRequest;
import com.pyramid.game.domain.users.dto.AppUserResponse;
import com.pyramid.game.domain.users.mapper.AppUserMapper;
import com.pyramid.game.domain.users.service.AppUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 21/04/2024
 * Time: 09:55
 * Project Name: pyramid-game-api
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "User Management")
public class AppUserController {

    private final AppUserService appUserService;
    private final AppUserMapper mapper;

    @PostMapping
    ResponseEntity<AppUserResponse> createNewUser(@Valid @RequestBody AppUserRequest appUserRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toDto(appUserService.createUser(mapper.toEntity(appUserRequest))));
    }

    @GetMapping
    ResponseEntity<PageDto<AppUserResponse>> listAllUsersPaginated(Pageable pageable){
        return ResponseEntity.ok(
                PaginationUtils.convertEntityPageToDtoPage(appUserService.getUserPaginated(pageable), mapper::toDtoList)
        );
    }

    @GetMapping("/all/partner/{partnercode}")
    ResponseEntity<PageDto<AppUserResponse>> listAllUsersPartnerPaginated(Pageable pageable,
                                                                          @PathVariable("partnercode") String partnercode){
        return ResponseEntity.ok(
                PaginationUtils.convertEntityPageToDtoPage(appUserService.listUserPartner(pageable, partnercode), mapper::toDtoList)
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<AppUserResponse> selectUserId(@PathVariable Long id) {
        return ResponseEntity
                .ok(mapper.toDto(appUserService.searchUser(id)));
    }

    @GetMapping("/login/{login}")
    ResponseEntity<List<AppUserResponse>> listAllUserLogin(@PathVariable String login) {
        return ResponseEntity
                .ok(mapper.toDtoList(appUserService.searchUserLogin(login)));
    }

    @GetMapping("/partner/{partnerCode}/{login}")
    ResponseEntity<AppUserResponse> selectPartnerUserLogin(@PathVariable("partnerCode") String partnerCode,
                                                           @PathVariable("login") String login) {
        return ResponseEntity
                .ok(mapper.toDto(appUserService.searchPartnerUserLogin(partnerCode, login)));
    }

    @PutMapping("/{id}")
    ResponseEntity<AppUserResponse> updateUserInfo(@Valid @RequestBody AppUserRequest appUserRequest, @PathVariable Long id) {
        return ResponseEntity
                .ok(mapper.toDto(appUserService.updateAppUserInfo(id, mapper.toEntity(appUserRequest))));
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        appUserService.deleteUser(id);
    }

    @PutMapping("/{id}/{status}")
    ResponseEntity<AppUserResponse> changeUserStatus(@PathVariable("id") Long id, @PathVariable("status") String status) {
        return ResponseEntity
                .ok(mapper.toDto(appUserService.updateAppUserStatus(id, status)));
    }

}
