package com.burgertable.burgertable.service.inventory;

import com.burgertable.burgertable.dto.inventory.InventoryPaginationDTO;
import com.burgertable.burgertable.entity.InventoryEntity;
import com.burgertable.burgertable.mapper.inventory.InventoryMapper;
import com.burgertable.burgertable.repository.inventory.InventoryRepository;
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
public class InventoryGetService {
    private final InventoryRepository inventoryRepository;

    public InventoryPaginationDTO getAll(int page, int PAGE_SIZE, String category) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<InventoryEntity> inventoryEntityPage = inventoryRepository.findByIngredientCategory(category, pageable);
        return InventoryMapper.INSTANCE.toInventoryPaginationDTO(inventoryEntityPage);
    }

    public List<String> getCategoryList() {
        return Optional.ofNullable(inventoryRepository.findByIngredientCategories())
                .orElse(Collections.emptyList());
    }
}
