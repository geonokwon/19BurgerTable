package com.burgertable.burgertable.service.ingredient;

import com.burgertable.burgertable.dto.ingredient.IngredientDTO;
import com.burgertable.burgertable.entity.IngredientEntity;
import com.burgertable.burgertable.entity.InventoryEntity;
import com.burgertable.burgertable.mapper.ingredient.IngredientMapper;
import com.burgertable.burgertable.repository.ingredient.IngredientRepository;
import com.burgertable.burgertable.repository.inventory.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientAddService {
    private final IngredientRepository ingredientRepository;
    private final InventoryRepository inventoryRepository;

    public boolean add(IngredientDTO ingredientDTO) {
        IngredientEntity ingredientEntity = IngredientMapper.INSTANCE.toIngredientEntity(ingredientDTO);
        if (ingredientEntity != null){
            ingredientRepository.save(ingredientEntity);
            InventoryEntity inventoryEntity = new InventoryEntity();
            inventoryEntity.setIngredient(ingredientEntity);
            inventoryEntity.setTotalQuantity(BigDecimal.ZERO);
            inventoryRepository.save(inventoryEntity);
            return true;
        }
     return false;
    }
}
