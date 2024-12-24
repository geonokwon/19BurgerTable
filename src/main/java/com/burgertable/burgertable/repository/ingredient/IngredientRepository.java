package com.burgertable.burgertable.repository.ingredient;


import com.burgertable.burgertable.entity.IngredientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {
    //Pagination
    @Query("SELECT i FROM IngredientEntity i  WHERE :category IS NULL OR :category = '' OR i.category = :category ORDER BY i.category ASC ")
    Page<IngredientEntity> findAllByCategory(@Param("category") String category, Pageable pageable);


    //카테고리 중복제거후 List 반환
    @Query("SELECT DISTINCT i.category FROM IngredientEntity i ORDER BY i.category ASC ")
    List<String> findDistinctCategories();

    //재고 구매리스트 추가에 필요한 카테고리별 재료 반환
    @Query("SELECT i.name FROM IngredientEntity i WHERE i.category = :category ")
    List<String> findNamesByCategory(@Param("category") String category);

    @Query("SELECT i.unit FROM IngredientEntity i WHERE i.name = :ingredientName")
    String findUnitByName(@Param("ingredientName") String ingredientName);
}
