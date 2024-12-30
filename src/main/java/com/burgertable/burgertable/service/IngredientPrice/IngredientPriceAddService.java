package com.burgertable.burgertable.service.IngredientPrice;

import com.burgertable.burgertable.dto.ingredientPrice.IngredientPriceDTO;
import com.burgertable.burgertable.entity.IngredientPriceEntity;
import com.burgertable.burgertable.entity.InventoryEntity;
import com.burgertable.burgertable.mapper.ingredientPrice.IngredientPriceMapper;
import com.burgertable.burgertable.repository.ingredientPrice.IngredientPriceRepository;
import com.burgertable.burgertable.repository.inventory.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientPriceAddService {

    private final IngredientPriceRepository ingredientPriceRepository;
    private final InventoryRepository inventoryRepository;

    @Transactional
    public boolean add(List<IngredientPriceDTO> ingredientPriceDTOs) {
        log.info("ingredientPriceDTOs: {}", ingredientPriceDTOs);
        try {
            for (IngredientPriceDTO ingredientDTO : ingredientPriceDTOs) {
                IngredientPriceEntity ingredientPriceEntity = IngredientPriceMapper.INSTANCE.toIngredientPriceEntity(ingredientDTO);
                InventoryEntity inventoryEntity = inventoryRepository.findInventoryByIngredientName(ingredientDTO.getIngredientName()).orElse(null);
                if (inventoryEntity == null) {
                    return false;
                }
                inventoryEntity.setTotalQuantity(
                        inventoryEntity.getTotalQuantity().add(ingredientPriceEntity.getTotalQuantity())
                );
                inventoryEntity.updateLowStockStatus();
                ingredientPriceEntity.setInventory(inventoryEntity);
                ingredientPriceRepository.save(ingredientPriceEntity);
            }
            return true;
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
