package com.burgertable.burgertable.dto.sales;

import com.burgertable.burgertable.dto.utils.PaginationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesLogPageDTO implements PaginationDTO<SalesLogDataDTO> {
    private List<SalesLogDataDTO> items;
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
