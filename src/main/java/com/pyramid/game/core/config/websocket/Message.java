package com.pyramid.game.core.config.websocket;

import lombok.Data;

/**
 * Created by Suvorov Vassilievitch
 * Date: 17/05/2024
 * Time: 01:08
 * Project Name: pyramid-game-api
 */
@Data
public class Message {
    private String from;
    private String text;
}
