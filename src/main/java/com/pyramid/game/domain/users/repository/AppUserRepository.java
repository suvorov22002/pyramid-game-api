package com.pyramid.game.domain.users.repository;

import com.pyramid.game.domain.partner.model.Partner;
import com.pyramid.game.domain.users.model.AppUser;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Page<AppUser> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<AppUser> findAppUserByPartnerOrderByCreatedAtDesc(Partner partner, Pageable pageable);
    List<AppUser> findAppUserByLoginLikeIgnoreCase(String login);
    Optional<AppUser> findAppUserByPartnerAndLogin(Partner partner, String login);

}
