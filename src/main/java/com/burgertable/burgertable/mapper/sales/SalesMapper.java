package com.burgertable.burgertable.mapper.sales;

import com.burgertable.burgertable.dto.sales.FeesDTO;
import com.burgertable.burgertable.dto.sales.SalesMonthDataDTO;
import com.burgertable.burgertable.dto.sales.SalesMonthPureDTO;
import com.burgertable.burgertable.dto.sales.SalesSaveDataDTO;
import com.burgertable.burgertable.entity.FeesEntity;
import com.burgertable.burgertable.entity.SalesEntity;
import com.burgertable.burgertable.entity.SalesMonthEntity;
import com.burgertable.burgertable.entity.SalesMonthPureEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SalesMapper {
    //객체간의 매핑을 자동으로 하는 MapStruct 사용해서 편하게 사용
    //null 값 처리를 해줌
    SalesMapper INSTANCE = Mappers.getMapper(SalesMapper.class);

    //SalesSaveDataDTO -> SalesEntity 매핑
    SalesEntity toSalesEntity(SalesSaveDataDTO salesSaveDataDTO);

    //SalesEntity ->  SalesSaveDataDTO 매핑
    SalesSaveDataDTO toSalesSaveDataDTO(SalesEntity salesEntity);

    //SalesMonthEntity -> SalesMonthDataDTO 매핑
    SalesMonthDataDTO toSalesMonthDataDTO(SalesMonthEntity salesMonthEntity);

    //SalesMonthPureDTO -> SalesMonthPureEntity 매핑
    SalesMonthPureEntity toSalesMonthPureEntity(SalesMonthPureDTO SalesMonthPureDTO);

    //FeesDTO -> FeesEntity 매핑
    FeesEntity toFeesEntity(FeesDTO feesDTO);
}

