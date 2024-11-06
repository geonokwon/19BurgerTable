package com.burgertable.burgertable.service.sales;

import com.burgertable.burgertable.dto.SalesSaveDataDTO;
import com.burgertable.burgertable.entity.SalesEntity;
import com.burgertable.burgertable.mapper.SalesMapper;
import com.burgertable.burgertable.repository.SalesRepository;
import com.burgertable.burgertable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaveService {
    private final UserRepository userRepository;
    private final SalesRepository salesRepository;

    public boolean save(SalesSaveDataDTO salesSaveDataDTO) {
        log.info("saveSales data: {}", salesSaveDataDTO);
        //매출DTO 를 매출 엔터티로 매핑
        SalesEntity salesEntity = SalesMapper.INSTANCE.toSalesEntity(salesSaveDataDTO);
        if (salesEntity == null) {
            return false;
        }
        //소셜금액만 합산해서 저장
        salesEntity.setSocialSales(socialTotal(salesSaveDataDTO));
        //user_num 으로 유저 객체 반환받아 저장
        salesEntity.setUser(userRepository.findByName(salesSaveDataDTO.getUserName()) );
        salesRepository.save(salesEntity);
        return true;
    }


    //SalesSaveDataDTO 클래스를 SalesEntity 로 변환
    //null 에 대한 해결책 생각해야함 Optional 은 너무 코드 가독성이 좋지못함!..
//    private SalesEntity toEntity(SalesSaveDataDTO salesSaveDataDTO) {
//        SalesEntity salesEntity = new SalesEntity();
//        salesEntity.setCardSales(salesSaveDataDTO.getCardSales());
//        salesEntity.setCashSales(salesSaveDataDTO.getCashSales());
//        salesEntity.setSimpleSales(salesSaveDataDTO.getSimpleSales());
//        salesEntity.setBaeminSales(salesSaveDataDTO.getBaeminSales());
//        salesEntity.setCoupangSales(salesSaveDataDTO.getCoupangSales());
//        salesEntity.setNaverSales(salesSaveDataDTO.getNaverSales());
//        salesEntity.setYogiyoSales(salesSaveDataDTO.getYogiyoSales());
//        salesEntity.setTanyoSales(salesSaveDataDTO.getTanyoSales());
//        salesEntity.setTotalSales(salesSaveDataDTO.getTotalSales());
//        salesEntity.setSalesDate(salesSaveDataDTO.getSalesDate());
//        salesEntity.setSocialSales(socialTotal(salesSaveDataDTO));
//        salesEntity.setUser(userRepository.findByName(salesSaveDataDTO.getUserName()) );
//        return salesEntity;
//    }

    //DTO 클래스를 소셜(외부)금액만 합계로 반환
    //java 8 이후 나온 Stream 을 사용해서 null 값 제거후 long 타입으로 변환후 sum 합을 반환
    public Long socialTotal(SalesSaveDataDTO dto) {
        return Stream.of(dto.getBaeminSales(), dto.getCoupangSales(), dto.getNaverSales(),
                        dto.getSimpleSales(), dto.getTanyoSales(), dto.getYogiyoSales())
                .filter(Objects::nonNull) //널 값 제거
                .mapToLong(Long::longValue) //Long 을 long 으로 변환
                .sum();
    }

}
