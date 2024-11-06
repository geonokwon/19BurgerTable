package com.burgertable.burgertable.mapper;

import com.burgertable.burgertable.dto.SalesSaveDataDTO;
import com.burgertable.burgertable.entity.SalesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SalesMapper {
    //객체간의 매핑을 자동으로 하는 MapStruct 사용해서 편하게 사용
    //null 값 처리를 해줌
    SalesMapper INSTANCE = Mappers.getMapper(SalesMapper.class);

    //SalesSaveDataDTO -> SalesEntity 매핑
    SalesEntity toSalesEntity (SalesSaveDataDTO salesSaveDataDTO);
    //SalesEntity ->  SalesSaveDataDTO 매핑
    SalesSaveDataDTO toSalesSaveDataDTO (SalesEntity salesEntity);

}
