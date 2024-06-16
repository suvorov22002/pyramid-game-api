package com.pyramid.game.core.config.websocket;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/05/2024
 * Time: 01:03
 * Project Name: pyramid-game-api
 */
@Data
@NoArgsConstructor
public class Stock {

    String name;
    String icon;
    float price;
    boolean increased;

    public Stock(String name, String icon, float price) {
        this.name = name;
        this.icon = icon;
        this.price = price;
    }
}
