package com.pyramid.game.domain.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 28/04/2024
 * Time: 03:17
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
@SuperBuilder
@RequiredArgsConstructor
public class RequestResponse<T> {

    private int codeResponse;
    private HttpStatus error;
    private String message;
    private List<T> data;
}
