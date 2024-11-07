package com.burgertable.burgertable.mapper;

import com.burgertable.burgertable.dto.SalesLogDataDTO;
import com.burgertable.burgertable.entity.SalesEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.text.NumberFormat;
import java.util.List;

@Mapper
public interface SalesLogMapper {
    //객체간의 매핑을 자동으로 하는 MapStruct 사용해서 편하게 사용
    //null 값 처리를 해줌
    SalesLogMapper INSTANCE = Mappers.getMapper(SalesLogMapper.class);

    //SalesEntity -> SalesLogDataDTO 매핑
    //포맷팅 후 처리
    @Mapping(target = "cardSales", ignore = true)
    @Mapping(target = "cashSales", ignore = true)
    @Mapping(target = "socialSales", ignore = true)
    @Mapping(target = "totalSales", ignore = true)
    //리스트 형태로 반환할거기 때문에 리스트 매핑
    List<SalesLogDataDTO> toSalesLogDataDTOList(List<SalesEntity> salesEntities);

    //리스트 형태로 맵핑 하여 각각 포매팅 처리
    @AfterMapping
    default void formatListFields(@MappingTarget List<SalesLogDataDTO> salesLogDataDTOList, List<SalesEntity> salesEntities) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        for (int i = 0; i < salesLogDataDTOList.size(); i++) {
            salesLogDataDTOList.get(i).setCardSales(numberFormat.format(salesEntities.get(i).getCardSales()));
            salesLogDataDTOList.get(i).setCashSales(numberFormat.format(salesEntities.get(i).getCashSales()));
            salesLogDataDTOList.get(i).setSocialSales(numberFormat.format(salesEntities.get(i).getSocialSales()));
            salesLogDataDTOList.get(i).setTotalSales(numberFormat.format(salesEntities.get(i).getTotalSales()));
        }
    }



}
