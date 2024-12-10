package com.burgertable.burgertable.mapper.ingredient;

import com.burgertable.burgertable.dto.ingredient.IngredientDTO;
import com.burgertable.burgertable.entity.IngredientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredientMapper {
    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

    //IngredientAddDTO -> IngredientEntity 매핑
    IngredientEntity toIngredientEntity(IngredientDTO ingredientDTO);

    //IngredientEntity -> IngredientAddDTO 매핑
    IngredientDTO toIngredientDTO(IngredientEntity ingredientEntity);

}
