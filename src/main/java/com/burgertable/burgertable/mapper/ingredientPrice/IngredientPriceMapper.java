package com.burgertable.burgertable.mapper.ingredientPrice;

import com.burgertable.burgertable.dto.ingredient.IngredientDTO;
import com.burgertable.burgertable.dto.ingredientPrice.IngredientPriceDTO;
import com.burgertable.burgertable.dto.ingredientPrice.IngredientPricePaginationDTO;
import com.burgertable.burgertable.entity.IngredientPriceEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface IngredientPriceMapper {
    IngredientPriceMapper INSTANCE = Mappers.getMapper(IngredientPriceMapper.class);

    //IngredientPriceDTO -> IngredientPriceEntity 매핑
    IngredientPriceEntity toIngredientPriceEntity(IngredientPriceDTO ingredientPriceDTO);

    //IngredientPriceEntity -> IngredientPriceDTO 매핑
    IngredientPriceDTO toIngredientPriceDTO(IngredientPriceEntity ingredientPriceEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateIngredientPriceEntity(IngredientPriceDTO ingredientPriceDTO, @MappingTarget IngredientPriceEntity ingredientPriceEntity);

    //PaginationDTO 매핑
    default IngredientPricePaginationDTO toIngredientPricePaginationDTO(Page<IngredientPriceEntity> ingredientPriceEntityPage) {
        return IngredientPricePaginationDTO.builder()
                .items(ingredientPriceEntityPage.getContent()
                        .stream()
                        .map(this::toIngredientPriceDTO).toList())
                .totalPages(ingredientPriceEntityPage.getTotalPages())
                .currentPage(ingredientPriceEntityPage.getNumber())
                .hasNext(ingredientPriceEntityPage.hasNext())
                .hasPrevious(ingredientPriceEntityPage.hasPrevious())
                .build();
    }



}
