package com.burgertable.burgertable.service.ingredient;

import com.burgertable.burgertable.dto.ingredient.IngredientDTO;
import com.burgertable.burgertable.entity.IngredientEntity;
import com.burgertable.burgertable.mapper.ingredient.IngredientMapper;
import com.burgertable.burgertable.repository.ingredient.IngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientAddService {
    private final IngredientRepository ingredientRepository;

    public boolean add(IngredientDTO ingredientDTO) {
        IngredientEntity ingredientEntity = IngredientMapper.INSTANCE.toIngredientEntity(ingredientDTO);
        if (ingredientEntity != null){
            ingredientRepository.save(ingredientEntity);
            return true;
        }
     return false;
    }
}
