package com.burgertable.burgertable.repository.ingredientPrice;

import com.burgertable.burgertable.dto.ingredientPrice.IngredientPriceDTO;
import com.burgertable.burgertable.entity.IngredientPriceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IngredientPriceRepository extends JpaRepository<IngredientPriceEntity, Long> {
    @Query("SELECT ip FROM IngredientPriceEntity ip " +
            "WHERE :category IS NULL OR :category = '' OR ip.inventory.ingredient.category = :category " +
            "ORDER BY ip.inventory.ingredient.category ASC ")
    Page<IngredientPriceEntity> findByIngredientCategory(@Param("category") String category, Pageable pageable);

    @Query("SELECT DISTINCT i.category " +
            "FROM IngredientEntity i " +
            "WHERE i.category IS NOT NULL " +
            "ORDER BY i.category ASC ")
    List<String> findByIngredientCategories();


    @Query("SELECT ipe " +
            "FROM IngredientPriceEntity ipe " +
            "JOIN FETCH ipe.inventory inv " +
            "JOIN FETCH inv.ingredient ing " +
            "WHERE ipe.id = :id ")
    Optional<IngredientPriceEntity> findId(@Param("id") Long id);
}
