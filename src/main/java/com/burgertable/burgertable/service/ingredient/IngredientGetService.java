package com.burgertable.burgertable.service.ingredient;

import com.burgertable.burgertable.dto.ingredient.IngredientDTO;
import com.burgertable.burgertable.dto.ingredient.IngredientPaginationDTO;
import com.burgertable.burgertable.entity.IngredientEntity;
import com.burgertable.burgertable.mapper.ingredient.IngredientMapper;
import com.burgertable.burgertable.repository.ingredient.IngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientGetService {
    private final IngredientRepository ingredientRepository;

    public IngredientPaginationDTO getList(String category, int page, int PAGE_SIZE) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<IngredientEntity> ingredientEntityPage  = ingredientRepository.findAllByCategory(category, pageable);
        return IngredientMapper.INSTANCE.toIngredientPaginationDTO(ingredientEntityPage);
    }

    public List<String> getCategoryList() {
        return Optional.ofNullable(ingredientRepository.findDistinctCategories())
                .orElse(Collections.emptyList());
    }

    public IngredientDTO getIngredient(Long id) {
        return IngredientMapper.INSTANCE.toIngredientDTO(ingredientRepository.findById(id).orElse(null));
    }
}
