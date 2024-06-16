package com.pyramid.game.core.config.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Created by Suvorov Vassilievitch
 * Date: 17/05/2024
 * Time: 01:11
 * Project Name: pyramid-game-api
 */
@Data
@AllArgsConstructor
public class OutputMessage {
    private String from;
    private String text;
    private String time;
}
