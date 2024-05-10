package com.pyramid.game.core.pagination;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/04/2024
 * Time: 18:57
 * Project Name: pyramid-game-api
 */
public class PaginationUtils {

    private PaginationUtils() {
    }

    /**
     * Converts a Page of entities to a PageDto of DTOs.
     * <p>
     * This is a generic method that can be used with any type of Entity and DTO
     * as long as an appropriate mapping function is provided.
     *
     * @param entityPage the Page of entities to be converted
     * @param <E>        the type of the Entity
     * @param <D>        The type of the DTO.
     * @param alreadyMappedPageContent       The page content that is already mapped
     * @return a PageDto of DTOs, preserving the original pagination information
     */
    public static <E, D> PageDto<D> convertEntityPageToDtoPage(Page<E> entityPage, List<D> alreadyMappedPageContent) {
        PageDto<D> pageDto = constructPageDto(entityPage);
        pageDto.setData(alreadyMappedPageContent);
        return pageDto;
    }

    /**
     * Converts a Page of entities to a PageDto of DTOs.
     * <p>
     * This is a generic method that can be used with any type of Entity and DTO
     * as long as an appropriate mapping function is provided.
     *
     * @param entityPage the Page of entities to be converted
     * @param <E>        the type of the Entity
     * @param <D>        The type of the DTO.
     * @param mappingFunction The function that should map list of entities to list of dtos
     * @return a PageDto of DTOs, preserving the original pagination information
     */
    public static <E, D> PageDto<D> convertEntityPageToDtoPage(Page<E> entityPage, Function<List<E>, List<D>> mappingFunction) {
        PageDto<D> pageDto = constructPageDto(entityPage);
        pageDto.setData(mappingFunction.apply(entityPage.getContent()));

        return pageDto;
    }

    private static <E, D> PageDto<D> constructPageDto(Page<E> entityPage) {
        PageDto<D> pageDto = new PageDto<>();
        pageDto.setPage(entityPage.getNumber() == 0 ? 1 : entityPage.getNumber() + 1);
        pageDto.setLimit(entityPage.getSize());
        pageDto.setTotalPages(entityPage.getTotalPages());
        pageDto.setTotalItems(entityPage.getTotalElements());
        return pageDto;
    }
}
