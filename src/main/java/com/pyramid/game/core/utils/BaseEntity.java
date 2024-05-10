package com.pyramid.game.core.utils;

import com.pyramid.game.core.validation.MandatoryField;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/04/2024
 * Time: 09:09
 * Project Name: pyramid-game-api
 */

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @MandatoryField
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
