package com.burgertable.burgertable.service.IngredientPrice;

import com.burgertable.burgertable.dto.ingredientPrice.IngredientPriceDTO;
import com.burgertable.burgertable.entity.IngredientEntity;
import com.burgertable.burgertable.entity.IngredientPriceEntity;
import com.burgertable.burgertable.mapper.ingredientPrice.IngredientPriceMapper;
import com.burgertable.burgertable.repository.ingredientPrice.IngredientPriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientPriceUpdateService {
    private final IngredientPriceRepository ingredientPriceRepository;


    public boolean update(IngredientPriceDTO ingredientPriceDTO) {
        Optional<IngredientPriceEntity> ingredientPriceEntity = ingredientPriceRepository.findId(ingredientPriceDTO.getId());
        if (ingredientPriceEntity.isPresent()) {
            IngredientPriceEntity ingredientPrice = ingredientPriceEntity.get();
            //기존값을 빼고 업데이트 된 수량을 더해준다
            ingredientPrice.getInventory().setTotalQuantity(ingredientPrice.getInventory().getTotalQuantity().subtract(ingredientPrice.getTotalQuantity()));
            IngredientPriceMapper.INSTANCE.updateIngredientPriceEntity(ingredientPriceDTO, ingredientPrice);
            ingredientPrice.getInventory().setTotalQuantity(ingredientPrice.getInventory().getTotalQuantity().add(ingredientPrice.getTotalQuantity()));
            ingredientPriceRepository.save(ingredientPrice);
            return true;
        }
        else
            return false;
    }
}
