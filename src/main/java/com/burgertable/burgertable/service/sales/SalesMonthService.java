package com.burgertable.burgertable.service.sales;

import com.burgertable.burgertable.dto.SalesMonthDataDTO;
import com.burgertable.burgertable.entity.SalesMonthEntity;
import com.burgertable.burgertable.mapper.SalesMapper;
import com.burgertable.burgertable.repository.SalesMonthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalesMonthService {

    private final SalesMonthRepository salesMonthRepository;


    //월별 매출을 넘겨주는 코드
    public SalesMonthDataDTO getSalesMonthData() {
        YearMonth currentYearMonth = YearMonth.now();
        //오늘 날짜로 하여금 월 별 총금액 데이터를 불러온다
        SalesMonthEntity loadMonthData = salesMonthRepository.findByMonth(currentYearMonth.toString());

        //엔티티 DTO 로 매핑하여 넘겨준다
        SalesMapper salesMapper = SalesMapper.INSTANCE;
        return salesMapper.toSalesMonthDataDTO(loadMonthData);
    }
}
