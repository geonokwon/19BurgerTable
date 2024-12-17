package com.burgertable.burgertable.service.inventory;

import com.burgertable.burgertable.entity.InventoryEntity;
import com.burgertable.burgertable.repository.inventory.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryAddService {

    private final InventoryRepository inventoryRepository;

    public boolean lowStockAdd(Long id, BigDecimal lowStock) {
        InventoryEntity inventoryEntity = inventoryRepository.findById(id).orElse(null);
        if (inventoryEntity != null){
            inventoryEntity.setLowStock(lowStock);
            inventoryEntity.updateLowStockStatus();
            inventoryRepository.save(inventoryEntity);
            return true;
        }
        else
            return false;
    }
}
