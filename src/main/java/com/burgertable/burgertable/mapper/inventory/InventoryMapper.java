package com.burgertable.burgertable.mapper.inventory;

import com.burgertable.burgertable.dto.inventory.InventoryDTO;
import com.burgertable.burgertable.dto.inventory.InventoryPaginationDTO;
import com.burgertable.burgertable.entity.InventoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface InventoryMapper {
    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    //InventoryEntity -> InventoryDTO 매핑
    InventoryDTO toInventoryDTO(InventoryEntity inventoryEntity);

    //PaginationDTO 로 매핑
    default InventoryPaginationDTO toInventoryPaginationDTO(Page<InventoryEntity> inventoryEntityPage) {
        return InventoryPaginationDTO.builder()
                .items(inventoryEntityPage.getContent()
                        .stream()
                        .map(this::toInventoryDTO).toList())
                .totalPages(inventoryEntityPage.getTotalPages())
                .currentPage(inventoryEntityPage.getNumber())
                .hasNext(inventoryEntityPage.hasNext())
                .hasPrevious(inventoryEntityPage.hasPrevious())
                .build();
    }

}
