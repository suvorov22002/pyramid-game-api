package com.pyramid.game.domain.partner.repository;

import com.pyramid.game.domain.partner.model.Parameters;
import com.pyramid.game.domain.partner.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParametersRepository extends JpaRepository<Parameters, Long> {

    Optional<Parameters> findParametersByPartner(Partner partner);

}
