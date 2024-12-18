package com.burgertable.burgertable.repository.inventory;


import com.burgertable.burgertable.entity.InventoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    @Query("SELECT iv FROM InventoryEntity iv " +
            "WHERE (:category IS NULL OR :category = '' OR iv.ingredient.category = :category)" +
            "ORDER BY iv.lowStockStatus DESC, iv.ingredient.category ASC")
    Page<InventoryEntity> findByIngredientCategory(@Param("category") String category, Pageable pageable);
    Optional<InventoryEntity> findByIngredientId(Long ingredientId);

    @Query("SELECT DISTINCT i.ingredient.category FROM InventoryEntity i WHERE i.ingredient.category IS NOT NULL ORDER BY i.ingredient.category ASC ")
    List<String> findByIngredientCategories();
}
