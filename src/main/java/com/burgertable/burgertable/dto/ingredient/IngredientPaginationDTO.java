package com.burgertable.burgertable.dto.ingredient;


import com.burgertable.burgertable.dto.utils.PaginationDTO;
import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientPaginationDTO implements PaginationDTO<IngredientDTO> {
    private List<IngredientDTO> items;
    //페이지네이션 관련
    private int totalPages;
    private int currentPage;
    private boolean hasNext;
    private boolean hasPrevious;
    private Map<String, Object> pagination;

    @Override
    public boolean hasNext() {
        return false;
    }
    @Override
    public boolean hasPrevious() {
        return false;
    }
}
