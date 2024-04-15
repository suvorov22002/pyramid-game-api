package com.pyramid.game.domain.partner.repository;

import com.pyramid.game.domain.partner.model.Parameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametersRepository extends JpaRepository<Parameters, Long> {
}
