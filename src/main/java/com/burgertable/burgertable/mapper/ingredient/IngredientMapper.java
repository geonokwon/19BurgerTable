package com.burgertable.burgertable.mapper.ingredient;

import com.burgertable.burgertable.dto.ingredient.IngredientDTO;
import com.burgertable.burgertable.dto.ingredient.IngredientPaginationDTO;
import com.burgertable.burgertable.entity.IngredientEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface IngredientMapper {
    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

    //IngredientAddDTO -> IngredientEntity 매핑
    IngredientEntity toIngredientEntity(IngredientDTO ingredientDTO);

    //IngredientEntity -> IngredientAddDTO 매핑
    IngredientDTO toIngredientDTO(IngredientEntity ingredientEntity);

    //IngredientDTO -> IngredientEntity 업데이트 매핑
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateIngredientEntity(IngredientDTO ingredientDTO, @MappingTarget IngredientEntity ingredientEntity);

    //PaginationDTO 로 매핑
    default IngredientPaginationDTO toIngredientPaginationDTO(Page<IngredientEntity> ingredientEntityPage) {
        return IngredientPaginationDTO.builder()
                .items(ingredientEntityPage.getContent()
                        .stream()
                        .map(this::toIngredientDTO).toList())
                .totalPages(ingredientEntityPage.getTotalPages())
                .currentPage(ingredientEntityPage.getNumber())
                .hasNext(ingredientEntityPage.hasNext())
                .hasPrevious(ingredientEntityPage.hasPrevious())
                .build();
    }
}
