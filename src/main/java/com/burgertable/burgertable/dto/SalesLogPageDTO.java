package com.burgertable.burgertable.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesLogPageDTO {
    private List<SalesLogDataDTO> salesLogData;
    private int totalPages;
    private int currentPage;
    private boolean hasNext;
    private boolean hasPrevious;
}
