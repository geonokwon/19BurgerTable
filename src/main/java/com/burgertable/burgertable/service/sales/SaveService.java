package com.burgertable.burgertable.service.sales;

import com.burgertable.burgertable.dto.SalesSaveDataDTO;
import com.burgertable.burgertable.entity.SalesEntity;
import com.burgertable.burgertable.repository.SalesRepository;
import com.burgertable.burgertable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaveService {
    private final UserRepository userRepository;
    private final SalesRepository salesRepository;

    public boolean save(SalesSaveDataDTO salesSaveDataDTO) {
        log.info("saveSales data: {}", salesSaveDataDTO);
        //매출DTO 를 매출 엔터티로 매핑
        SalesEntity salesEntity = toEntity(salesSaveDataDTO);
        if (salesEntity == null) {
            return false;
        }
        salesRepository.save(salesEntity);
        return true;
    }


    //DTO 클래스를 Entity 로 변환
    private SalesEntity toEntity(SalesSaveDataDTO salesSaveDataDTO) {
        SalesEntity salesEntity = new SalesEntity();
        salesEntity.setCardSales(salesSaveDataDTO.getCardSales());
        salesEntity.setCashSales(salesSaveDataDTO.getCashSales());
        salesEntity.setSimpleSales(salesSaveDataDTO.getSimpleSales());
        salesEntity.setBaeminSales(salesSaveDataDTO.getBaeminSales());
        salesEntity.setCoupangSales(salesSaveDataDTO.getCoupangSales());
        salesEntity.setNaverSales(salesSaveDataDTO.getNaverSales());
        salesEntity.setYogiyoSales(salesSaveDataDTO.getYogiyoSales());
        salesEntity.setTanyoSales(salesSaveDataDTO.getTanyoSales());
        salesEntity.setTotalSales(salesSaveDataDTO.getTotalSales());
        salesEntity.setSalesDate(salesSaveDataDTO.getSalesDate());
        salesEntity.setSocialSales(socialTotal(salesSaveDataDTO));
        salesEntity.setUser(userRepository.findByName(salesSaveDataDTO.getUserName()) );
        return salesEntity;
    }

    //DTO 클래스를 소셜(외부)금액만 합계로 반환
    public Long socialTotal(SalesSaveDataDTO salesSaveDataDTO) {
     return  salesSaveDataDTO.getBaeminSales() +
             salesSaveDataDTO.getCoupangSales() +
             salesSaveDataDTO.getNaverSales() +
             salesSaveDataDTO.getSimpleSales() +
             salesSaveDataDTO.getTanyoSales() +
             salesSaveDataDTO.getYogiyoSales();
    }


}
