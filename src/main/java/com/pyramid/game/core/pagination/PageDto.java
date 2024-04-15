package com.pyramid.game.core.pagination;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/04/2024
 * Time: 18:56
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
public class PageDto<D> {
    private Integer limit;
    private Long totalItems;
    private Integer page;
    private Integer totalPages;
    private List<D> data;
}
