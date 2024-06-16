package com.pyramid.game.domain.users.service.impl;

import com.pyramid.game.core.utils.Constants;
import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.partner.repository.PartnerRepository;
import com.pyramid.game.domain.salle.model.Salle;
import com.pyramid.game.domain.salle.model.enums.SalleStatus;
import com.pyramid.game.domain.users.mapper.AppUserMapper;
import com.pyramid.game.domain.users.model.AppUser;
import com.pyramid.game.domain.users.repository.AppUserRepository;
import com.pyramid.game.domain.users.service.AppUserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 21/04/2024
 * Time: 09:09
 * Project Name: pyramid-game-api
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AppUserServiceImplement implements AppUserService {

    private final AppUserRepository appUserRepo;
    private final PartnerRepository partnerRepo;
    private final AppUserMapper appUSerMapper;
 //   private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AppUser createUser(AppUser appUser) {
        appUserRepo.findAppUserByPartnerAndLogin(appUser.getPartner(), appUser.getLogin())
                .ifPresent(
                        user -> {
                            throw new EntityExistsException(Constants.USER_LOGIN_EXIST);
                        }
                );

    //    String encodedPassWord = bCryptPasswordEncoder.encode(appUser.getPassword());
    //    appUser.setPassword(encodedPassWord);
        appUser.setCreatedAt(LocalDateTime.now());
        return appUserRepo.save(appUser);
    }

    @Override
    public Page<AppUser> listUserPartner(Pageable pageable, String partnerCode) {

        Partner partner = partnerRepo.findPartnerByCodePartner(partnerCode).orElseThrow(
                () -> new EntityNotFoundException(Constants.PARTNER_NOT_FOUND)
        );

        return appUserRepo.findAppUserByPartnerOrderByCreatedAtDesc(partner, pageable);
    }

    @Override
    public Page<AppUser> getUserPaginated(Pageable pageable) {
        return appUserRepo.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public AppUser searchUser(Long id) {
        return appUserRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Constants.USER_NOT_FOUND)
        );
    }

    @Override
    public List<AppUser> searchUserLogin(String login) {
        return appUserRepo.findAppUserByLoginLikeIgnoreCase(login);
    }

    @Override
    public AppUser searchPartnerUserLogin(String partnerCode, String login) {
        Partner partner = partnerRepo.findPartnerByCodePartner(partnerCode).orElseThrow(
                () -> new EntityNotFoundException(Constants.PARTNER_NOT_FOUND)
        );

        return appUserRepo.findAppUserByPartnerAndLogin(partner, login).orElseThrow(
                () -> new EntityNotFoundException(Constants.USER_NOT_FOUND)
        );
    }

    @Override
    public AppUser updateAppUserInfo(Long id, AppUser appUser) {
        AppUser existingUser = appUserRepo.findAppUserByPartnerAndLogin(appUser.getPartner(), appUser.getLogin())
                .orElseThrow( () -> new EntityNotFoundException(Constants.USER_NOT_FOUND));

        AppUser updatedUser = appUSerMapper.update(appUser, existingUser);
        return appUserRepo.save(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        AppUser user = searchUser(id);
        appUserRepo.delete(user);
    }

    @Override
    public AppUser suspendUser(AppUser user) {
        AppUser existingUser = searchUser(user.getId());
        existingUser.setEnabled(Boolean.FALSE);
        return appUserRepo.save(existingUser);
    }

    @Override
    public AppUser updateAppUserStatus(Long id, String status) {
        AppUser existingUser = searchUser(id);
        existingUser.setEnabled(Boolean.valueOf(status));
        return appUserRepo.save(existingUser);
    }

    @Override
    public AppUser updateAppUserBalance(Long id, Double balance) {
        AppUser existingUser = searchUser(id);
        existingUser.setBalance(balance);
        return appUserRepo.save(existingUser);
    }
}
