package com.burgertable.burgertable.utils;

import com.burgertable.burgertable.dto.ingredient.IngredientPaginationDTO;
import com.burgertable.burgertable.dto.utils.PaginationDTO;

import java.util.HashMap;
import java.util.Map;

public class PaginationUtil {

    public static <T extends PaginationDTO<?>> T addPaginationData(T dto, int pageBlockSize) {
        int currentPage = dto.getCurrentPage();
        int totalPages = dto.getTotalPages();

        // 페이지네이션 계산
        int startPage = (currentPage / pageBlockSize) * pageBlockSize + 1;
        int endPage = Math.min(startPage + pageBlockSize - 1, totalPages);

        boolean hasPreviousGroup = startPage > 1;
        boolean hasNextGroup = endPage < totalPages;

        // 페이지네이션 데이터 생성
        Map<String, Object> paginationData = new HashMap<>();
        paginationData.put("startPage", startPage);
        paginationData.put("endPage", endPage);
        paginationData.put("hasPreviousGroup", hasPreviousGroup);
        paginationData.put("hasNextGroup", hasNextGroup);

        dto.setPagination(paginationData);

        return dto;
    }
}
