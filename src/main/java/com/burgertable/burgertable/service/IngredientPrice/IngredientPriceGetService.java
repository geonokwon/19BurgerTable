package com.burgertable.burgertable.service.IngredientPrice;

import com.burgertable.burgertable.dto.ingredientPrice.IngredientPriceDTO;
import com.burgertable.burgertable.dto.ingredientPrice.IngredientPricePaginationDTO;
import com.burgertable.burgertable.entity.IngredientPriceEntity;
import com.burgertable.burgertable.mapper.ingredientPrice.IngredientPriceMapper;
import com.burgertable.burgertable.repository.ingredient.IngredientRepository;
import com.burgertable.burgertable.repository.ingredientPrice.IngredientPriceRepository;
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
public class IngredientPriceGetService {

    private final IngredientRepository ingredientRepository;
    private final IngredientPriceRepository ingredientPriceRepository;

    public IngredientPricePaginationDTO getAll(String category, int page, int PAGE_SIZE) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<IngredientPriceEntity> ingredientPriceEntities = ingredientPriceRepository.findByIngredientCategory(category, pageable);
        return IngredientPriceMapper.INSTANCE.toIngredientPricePaginationDTO(ingredientPriceEntities);
    }

    public List<String> getCategoryList() {
        return Optional.ofNullable(ingredientPriceRepository.findByIngredientCategories())
                .orElse(Collections.emptyList());

    }

    public List<String> getIngredientNames(String category){
        return Optional.ofNullable(ingredientRepository.findNamesByCategory(category))
                .orElse(Collections.emptyList());
    }

    public String getIngredientUnit(String ingredientName) {
        return Optional.ofNullable(ingredientRepository.findUnitByName(ingredientName))
                .orElse("");
    }

    public IngredientPriceDTO get(Long id) {
        return ingredientPriceRepository.findId(id)
                .map(IngredientPriceMapper.INSTANCE::toIngredientPriceDTO)
                .orElse(null);
    }
}
