package com.pyramid.game.domain.shift.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Created by Suvorov Vassilievitch
 * Date: 11/06/2024
 * Time: 15:22
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
public class ShiftKeno extends Shift{

    protected Shift selectData() {
        return this;
    }
}
