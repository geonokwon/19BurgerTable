package com.burgertable.burgertable.service.ingredient;

import com.burgertable.burgertable.repository.ingredient.IngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientDeleteService {
    private final IngredientRepository ingredientRepository;


    public boolean deleteIngredient(Long id) {
        return ingredientRepository.existsById(id);
    }
}
