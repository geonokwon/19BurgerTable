package com.burgertable.burgertable.service.IngredientPrice;

import com.burgertable.burgertable.entity.IngredientPriceEntity;
import com.burgertable.burgertable.repository.ingredientPrice.IngredientPriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientPriceDeleteService {
    private final IngredientPriceRepository ingredientPriceRepository;

    public boolean delete(Long id){
        Optional<IngredientPriceEntity> ingredientPriceEntity = ingredientPriceRepository.findId(id);
        if(ingredientPriceEntity.isPresent()){
            IngredientPriceEntity ingredientPrice = ingredientPriceEntity.get();
            ingredientPrice.getInventory().setTotalQuantity(ingredientPrice.getInventory().getTotalQuantity().subtract(ingredientPrice.getTotalQuantity()));
            ingredientPrice.getInventory().updateLowStockStatus();
            ingredientPriceRepository.delete(ingredientPrice);
            return true;
        }
        else
            return false;
    }


}
