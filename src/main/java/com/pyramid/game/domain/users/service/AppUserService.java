package com.pyramid.game.domain.users.service;

import com.pyramid.game.domain.users.model.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface AppUserService {

    AppUser createUser(AppUser appUser);
    Page<AppUser> listUserPartner(Pageable pageable, String partnerCode);
    Page<AppUser> getUserPaginated(Pageable pageable);
    AppUser searchUser(Long id);
    List<AppUser> searchUserLogin(String login);
    AppUser searchPartnerUserLogin(String partnerCode, String login);
    @Modifying
    AppUser updateAppUserInfo(Long id, AppUser appUser);
    void deleteUser(Long id);
    @Modifying
    AppUser suspendUser(AppUser user);
    @Modifying
    AppUser updateAppUserStatus(Long id, String status);

}
