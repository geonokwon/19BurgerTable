package com.burgertable.burgertable.service.ingredient;

import com.burgertable.burgertable.dto.ingredient.IngredientDTO;
import com.burgertable.burgertable.entity.IngredientEntity;
import com.burgertable.burgertable.mapper.ingredient.IngredientMapper;
import com.burgertable.burgertable.repository.ingredient.IngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientGetService {
    private final IngredientRepository ingredientRepository;

    public List<IngredientDTO> getList(String category) {
        List<IngredientEntity> ingredientDTOList = ingredientRepository.findByCategoryOrAll(category);
        return Optional.ofNullable(ingredientDTOList)
                .orElse(Collections.emptyList())
                .stream().map(IngredientMapper.INSTANCE::toIngredientDTO)
                .collect(Collectors.toList());
    }

    public List<String> getCategoryList() {
        return Optional.ofNullable(ingredientRepository.findDistinctCategories())
                .orElse(Collections.emptyList());
    }
}
