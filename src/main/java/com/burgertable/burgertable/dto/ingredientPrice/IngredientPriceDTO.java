package com.burgertable.burgertable.dto.ingredientPrice;

import com.burgertable.burgertable.entity.InventoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientPriceDTO {
    private Long id;
    private InventoryEntity inventory;
    private String supplier;
    private LocalDate priceDate;
    private BigDecimal totalQuantity;
    private Long totalPrice;
    private String ingredientName;

}
