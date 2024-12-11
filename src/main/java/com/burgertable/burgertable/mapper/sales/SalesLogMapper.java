package com.burgertable.burgertable.mapper.sales;

import com.burgertable.burgertable.dto.ingredient.IngredientPaginationDTO;
import com.burgertable.burgertable.dto.sales.SalesLogDataDTO;
import com.burgertable.burgertable.dto.sales.SalesLogPageDTO;
import com.burgertable.burgertable.entity.IngredientEntity;
import com.burgertable.burgertable.entity.SalesEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.text.NumberFormat;
import java.util.List;

@Mapper
public interface SalesLogMapper {
    //객체간의 매핑을 자동으로 하는 MapStruct 사용해서 편하게 사용
    //null 값 처리를 해줌
    SalesLogMapper INSTANCE = Mappers.getMapper(SalesLogMapper.class);

    //SalesEntity -> SalesLogDataDTO 매핑
    SalesLogDataDTO toSalesLogDataDTO(SalesEntity salesEntity);

    //PaginationDTO 로 매핑
    default SalesLogPageDTO toSalesLogPageDTO(Page<SalesEntity> salesEntityPage) {
        return SalesLogPageDTO.builder()
                .items(salesEntityPage.getContent()
                        .stream()
                        .map(this::toSalesLogDataDTO).toList())
                .totalPages(salesEntityPage.getTotalPages())
                .currentPage(salesEntityPage.getNumber())
                .hasNext(salesEntityPage.hasNext())
                .hasPrevious(salesEntityPage.hasPrevious())
                .build();
    }



}
