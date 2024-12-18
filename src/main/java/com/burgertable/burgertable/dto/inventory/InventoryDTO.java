package com.burgertable.burgertable.dto.inventory;

import com.burgertable.burgertable.entity.IngredientEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {

    private Long id;
    private IngredientEntity ingredient;
    private BigDecimal totalQuantity;
    private LocalDateTime updateDate;
    private BigDecimal lowStock;
    private boolean lowStockStatus;

}
