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

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientUpdateService {
    private final IngredientRepository ingredientRepository;
    private final InventoryRepository inventoryRepository;

    public boolean updateIngredient(IngredientDTO ingredientDTO) {
        Optional<IngredientEntity> getIngredient = ingredientRepository.findById(ingredientDTO.getId());
        if (getIngredient.isPresent()) {
            //재료 업데이트
            IngredientEntity ingredient = getIngredient.get();
            IngredientMapper.INSTANCE.updateIngredientEntity(ingredientDTO, ingredient);
            ingredientRepository.save(ingredient);

            //재고 업데이트
            Optional<InventoryEntity> inventoryEntity = inventoryRepository.findByIngredientId(ingredient.getId());
            if (inventoryEntity.isPresent()){
                InventoryEntity inventory = inventoryEntity.get();
                inventory.setIngredient(ingredient);
                inventoryRepository.save(inventory);
            }
            return true;
        }
        return false;
    }
}
