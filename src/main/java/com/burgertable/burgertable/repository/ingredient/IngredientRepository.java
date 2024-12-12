package com.burgertable.burgertable.repository.ingredient;


import com.burgertable.burgertable.entity.IngredientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {
    //Pagination
    @Query("SELECT i FROM IngredientEntity i  WHERE :category IS NULL OR :category = '' OR i.category = :category ORDER BY i.category ASC ")
    Page<IngredientEntity> findAllByCategory(@Param("category") String category, Pageable pageable);


    //카테고리 중복제거후 List 반환
    @Query("SELECT DISTINCT i.category FROM IngredientEntity i ORDER BY i.category ASC ")
    List<String> findDistinctCategories();
}
