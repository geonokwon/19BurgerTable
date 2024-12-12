package com.burgertable.burgertable.service.ingredient;

import com.burgertable.burgertable.dto.ingredient.IngredientDTO;
import com.burgertable.burgertable.entity.IngredientEntity;
import com.burgertable.burgertable.mapper.ingredient.IngredientMapper;
import com.burgertable.burgertable.repository.ingredient.IngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientUpdateService {
    private final IngredientRepository ingredientRepository;

    public boolean updateIngredient(IngredientDTO ingredientDTO) {
        Optional<IngredientEntity> getIngredient = ingredientRepository.findById(ingredientDTO.getId());
        if (getIngredient.isPresent()) {
            IngredientEntity ingredient = getIngredient.get();
            IngredientMapper.INSTANCE.updateIngredientEntity(ingredientDTO, ingredient);
            ingredientRepository.save(ingredient);
            return true;
        }
        return false;
    }
}
