package com.pyramid.game.domain.slip.repository;

import com.pyramid.game.domain.bet.model.BetKeno;
import com.pyramid.game.domain.slip.model.SlipKeno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlipKenoRepository extends JpaRepository<SlipKeno, Long> {

    List<SlipKeno> findSlipKenoByBetKeno(BetKeno betKeno);
}
