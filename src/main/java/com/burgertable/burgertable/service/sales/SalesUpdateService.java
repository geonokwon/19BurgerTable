package com.burgertable.burgertable.service.sales;

import com.burgertable.burgertable.dto.sales.SalesSaveDataDTO;
import com.burgertable.burgertable.entity.SalesEntity;
import com.burgertable.burgertable.entity.SalesMonthEntity;
import com.burgertable.burgertable.mapper.sales.SalesMapper;
import com.burgertable.burgertable.repository.sales.SalesMonthRepository;
import com.burgertable.burgertable.repository.sales.SalesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.YearMonth;
import java.time.ZoneId;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalesUpdateService {
    private final SalesSaveService salesSaveService;
    private final SalesRepository salesRepository;
    private final SalesMonthRepository salesMonthRepository;


    public boolean salesDataUpdate(SalesSaveDataDTO salesSaveDataDTO) {

        SalesEntity salesEntity = salesRepository.findById(salesSaveDataDTO.getSalesId()).orElse(null);

        if (salesEntity == null) {
            return false;
        }

        // 기존 날짜와 새로운 날짜 추출
        Timestamp originalSalesDate = salesEntity.getSalesDate(); // 기존 저장된 날짜
        Timestamp newSalesDate = salesSaveDataDTO.getSalesDate(); // 수정된 새로운 날짜

        // 날짜가 같은 달인지 확인
        YearMonth originalMonth = YearMonth.from(originalSalesDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        YearMonth newMonth = YearMonth.from(newSalesDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        // 매출 데이터 업데이트
        SalesEntity updateSalesEntity = SalesMapper.INSTANCE.toSalesEntity(salesSaveDataDTO);
        updateSalesEntity.setUser(salesEntity.getUser()); // 기존 유저 정보 유지
        updateSalesEntity.setSocialSales(salesSaveService.socialTotal(salesSaveDataDTO)); // 소셜 매출 계산
        salesRepository.save(updateSalesEntity);

        // 날짜가 다른 경우
        if (!originalMonth.equals(newMonth)) {
            handleMonthChange(originalMonth, newMonth, salesEntity, updateSalesEntity);
        }

        return true;
    }

    private void handleMonthChange(YearMonth originalMonth, YearMonth newMonth, SalesEntity originalSalesEntity, SalesEntity updatedSalesEntity) {
        String originalYearMonthStr = originalMonth.toString();
        String newYearMonthStr = newMonth.toString();

        // 기존 Month 데이터에서 제거
        SalesMonthEntity originalMonthEntity = salesMonthRepository.findByMonth(originalYearMonthStr);
        if (originalMonthEntity != null) {
            subtractSalesFromMonth(originalMonthEntity, originalSalesEntity);

            // 기존 Month가 비어 있으면 삭제
            if (isMonthDataEmpty(originalMonthEntity)) {
                salesMonthRepository.delete(originalMonthEntity);
            } else {
                salesMonthRepository.save(originalMonthEntity);
            }
        }

        // 새로운 Month 데이터 추가
        SalesMonthEntity newMonthEntity = salesMonthRepository.findByMonth(newYearMonthStr);
        if (newMonthEntity == null) {
            // 새로운 월 데이터 생성 및 초기화
            newMonthEntity = new SalesMonthEntity();
            newMonthEntity.setMonth(newYearMonthStr);
            initializeMonthData(newMonthEntity); // 초기화 처리
        }

        addSalesToMonth(newMonthEntity, updatedSalesEntity);
        salesMonthRepository.save(newMonthEntity); // 새로운 데이터 저장
    }

    private void initializeMonthData(SalesMonthEntity monthEntity) {
        monthEntity.setCardMonth(0L);
        monthEntity.setCashMonth(0L);
        monthEntity.setSimpleMonth(0L);
        monthEntity.setBaeminMonth(0L);
        monthEntity.setCoupangMonth(0L);
        monthEntity.setYogiyoMonth(0L);
        monthEntity.setNaverMonth(0L);
        monthEntity.setTanyoMonth(0L);
        monthEntity.setTotalMonth(0L);
        monthEntity.setSalesMonthPure(null); // 수수료 데이터 초기화
        monthEntity.setFees(null); // 관련 수수료 초기화
    }

    private void subtractSalesFromMonth(SalesMonthEntity salesMonth, SalesEntity salesEntity) {
        salesMonth.setCardMonth(salesMonth.getCardMonth() - salesEntity.getCardSales());
        salesMonth.setCashMonth(salesMonth.getCashMonth() - salesEntity.getCashSales());
        salesMonth.setSimpleMonth(salesMonth.getSimpleMonth() - salesEntity.getSimpleSales());
        salesMonth.setBaeminMonth(salesMonth.getBaeminMonth() - salesEntity.getBaeminSales());
        salesMonth.setCoupangMonth(salesMonth.getCoupangMonth() - salesEntity.getCoupangSales());
        salesMonth.setYogiyoMonth(salesMonth.getYogiyoMonth() - salesEntity.getYogiyoSales());
        salesMonth.setNaverMonth(salesMonth.getNaverMonth() - salesEntity.getNaverSales());
        salesMonth.setTanyoMonth(salesMonth.getTanyoMonth() - salesEntity.getTanyoSales());
        salesMonth.setTotalMonth(salesMonth.getTotalMonth() - salesEntity.getTotalSales());
    }

    private void addSalesToMonth(SalesMonthEntity salesMonth, SalesEntity salesEntity) {
        salesMonth.setCardMonth(salesMonth.getCardMonth() + salesEntity.getCardSales());
        salesMonth.setCashMonth(salesMonth.getCashMonth() + salesEntity.getCashSales());
        salesMonth.setSimpleMonth(salesMonth.getSimpleMonth() + salesEntity.getSimpleSales());
        salesMonth.setBaeminMonth(salesMonth.getBaeminMonth() + salesEntity.getBaeminSales());
        salesMonth.setCoupangMonth(salesMonth.getCoupangMonth() + salesEntity.getCoupangSales());
        salesMonth.setYogiyoMonth(salesMonth.getYogiyoMonth() + salesEntity.getYogiyoSales());
        salesMonth.setNaverMonth(salesMonth.getNaverMonth() + salesEntity.getNaverSales());
        salesMonth.setTanyoMonth(salesMonth.getTanyoMonth() + salesEntity.getTanyoSales());
        salesMonth.setTotalMonth(salesMonth.getTotalMonth() + salesEntity.getTotalSales());
    }

    private boolean isMonthDataEmpty(SalesMonthEntity salesMonth) {
        return salesMonth.getCardMonth() == 0 &&
                salesMonth.getCashMonth() == 0 &&
                salesMonth.getSimpleMonth() == 0 &&
                salesMonth.getBaeminMonth() == 0 &&
                salesMonth.getCoupangMonth() == 0 &&
                salesMonth.getYogiyoMonth() == 0 &&
                salesMonth.getNaverMonth() == 0 &&
                salesMonth.getTanyoMonth() == 0 &&
                salesMonth.getTotalMonth() == 0;
    }
}
