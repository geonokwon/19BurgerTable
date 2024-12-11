package com.burgertable.burgertable.dto.utils;

import java.util.List;
import java.util.Map;

public interface PaginationDTO<T> {
    List<T> getItems();
    int getTotalPages();
    int getCurrentPage();
    boolean hasNext();
    boolean hasPrevious();

    void setPagination(Map<String, Object> paginationData);
}
