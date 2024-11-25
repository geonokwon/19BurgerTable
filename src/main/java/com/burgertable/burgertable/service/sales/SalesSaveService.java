package com.burgertable.burgertable.service.sales;

import com.burgertable.burgertable.dto.sales.FeesDTO;
import com.burgertable.burgertable.dto.sales.SalesMonthPureDTO;
import com.burgertable.burgertable.dto.sales.SalesSaveDataDTO;
import com.burgertable.burgertable.entity.FeesEntity;
import com.burgertable.burgertable.entity.SalesEntity;
import com.burgertable.burgertable.entity.SalesMonthEntity;
import com.burgertable.burgertable.entity.SalesMonthPureEntity;
import com.burgertable.burgertable.mapper.sales.SalesMapper;
import com.burgertable.burgertable.repository.sales.FeesRepository;
import com.burgertable.burgertable.repository.sales.SalesMonthPureRepository;
import com.burgertable.burgertable.repository.sales.SalesMonthRepository;
import com.burgertable.burgertable.repository.sales.SalesRepository;
import com.burgertable.burgertable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalesSaveService {
    private final UserRepository userRepository;
    private final SalesRepository salesRepository;
    private final SalesMonthRepository salesMonthRepository;
    private final SalesMonthPureRepository salesMonthPureRepository;
    private final FeesRepository feesRepository;

    @Transactional
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
        SalesEntity saveSales = salesRepository.save(salesEntity);

        //일 매출 저장할때 월 매출 반영하기 위함
        updateMonthlySummary(saveSales.getSalesDate());

        return true;
    }

    //월별 총 매출 저장함수
    private void updateMonthlySummary(Timestamp salesDate) {
        YearMonth month = YearMonth.from(salesDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        String yearMonthStr = month.toString();

        //기존 데이터가 있는지 확인
        SalesMonthEntity salesMonth = salesMonthRepository.findByMonth(yearMonthStr);
        if (salesMonth == null){
            salesMonth = new SalesMonthEntity();
            salesMonth.setMonth(yearMonthStr);
        }

        //해당 월의 매출 합계 계산
        Long cardSum = salesRepository.sumCardSalesByMonth(yearMonthStr);
        log.info("cardSum: {}", cardSum);
        Long cashSum = salesRepository.sumCashSalesByMonth(yearMonthStr);
        Long simpleSum = salesRepository.sumSimpleSalesByMonth(yearMonthStr);
        Long baeminSum = salesRepository.sumBaeminSalesByMonth(yearMonthStr);
        Long coupangSum = salesRepository.sumCoupangSalesByMonth(yearMonthStr);
        Long yogiyoSum = salesRepository.sumYogiyoSalesByMonth(yearMonthStr);
        Long naverSum = salesRepository.sumNaverSalesByMonth(yearMonthStr);
        Long tanyoSum = salesRepository.sumTanyoSalesByMonth(yearMonthStr);
        Long totalSum = salesRepository.sumTotalSalesByMonth(yearMonthStr);

        // 매출 합계를 SalesMonthEntity에 설정
        salesMonth.setCardMonth(cardSum != null ? cardSum : 0L);
        salesMonth.setCashMonth(cashSum != null ? cashSum : 0L);
        salesMonth.setSimpleMonth(simpleSum != null ? simpleSum : 0L);
        salesMonth.setBaeminMonth(baeminSum != null ? baeminSum : 0L);
        salesMonth.setCoupangMonth(coupangSum != null ? coupangSum : 0L);
        salesMonth.setYogiyoMonth(yogiyoSum != null ? yogiyoSum : 0L);
        salesMonth.setNaverMonth(naverSum != null ? naverSum : 0L);
        salesMonth.setTanyoMonth(tanyoSum != null ? tanyoSum : 0L);
        salesMonth.setTotalMonth(totalSum != null ? totalSum : 0L);

        //순수익이 저장되어 있다면 수수료 다시 계산
        if (salesMonth.getSalesMonthPure() != null){
            FeesDTO newFeesDTO = calculateFees(salesMonth, salesMonth.getSalesMonthPure());
            FeesEntity feesEntity = SalesMapper.INSTANCE.toFeesEntity(newFeesDTO);
            feesRepository.save(feesEntity);
        }

        //업데이트된 집계 데이터 저장
        salesMonthRepository.save(salesMonth);
    }

    //DTO 클래스를 소셜(외부)금액만 합계로 반환
    //java 8 이후 나온 Stream 을 사용해서 null 값 제거후 long 타입으로 변환후 sum 합을 반환
    public Long socialTotal(SalesSaveDataDTO dto) {
        return Stream.of(dto.getBaeminSales(), dto.getCoupangSales(), dto.getNaverSales(),
                        dto.getSimpleSales(), dto.getTanyoSales(), dto.getYogiyoSales())
                .filter(Objects::nonNull) //널 값 제거
                .mapToLong(Long::longValue) //Long 을 long 으로 변환
                .sum();
    }


    //순수익 받아와 저장
    public FeesDTO setSalesMonthPureToFees(SalesMonthPureDTO salesMonthPureDTO) {
        SalesMonthPureEntity salesMonthPureEntity = SalesMapper.INSTANCE.toSalesMonthPureEntity(salesMonthPureDTO);
        SalesMonthPureEntity saveMonthPureEntity = salesMonthPureRepository.save(salesMonthPureEntity);

        if (saveMonthPureEntity != null){
            SalesMonthEntity salesMonthEntity = salesMonthRepository.findByMonth(saveMonthPureEntity.getMonth());

            FeesDTO feesDTO = calculateFees(salesMonthEntity, saveMonthPureEntity);
            FeesEntity feesEntity = SalesMapper.INSTANCE.toFeesEntity(feesDTO);
            FeesEntity saveFeesEntity = feesRepository.save(feesEntity);

            salesMonthEntity.setFees(saveFeesEntity);
            salesMonthEntity.setSalesMonthPure(saveMonthPureEntity);

            return feesDTO;
        }
        return null;
    }

    //수수료 저장 및 계산 함수
    public FeesDTO calculateFees(SalesMonthEntity salesMonthEntity, SalesMonthPureEntity salesMonthPureEntity){
        Long card = salesMonthPureEntity.getCardMonthPure() != 0 ? salesMonthEntity.getCardMonth() - salesMonthPureEntity.getCardMonthPure() : 0;
        Long simple = salesMonthPureEntity.getSimpleMonthPure() != 0 ? salesMonthEntity.getSimpleMonth() - salesMonthPureEntity.getSimpleMonthPure() : 0;
        Long beamin = salesMonthPureEntity.getBaeminMonthPure() != 0 ? salesMonthEntity.getBaeminMonth() - salesMonthPureEntity.getBaeminMonthPure() : 0;
        Long coupang = salesMonthPureEntity.getCoupangMonthPure() != 0 ? salesMonthEntity.getCoupangMonth() - salesMonthPureEntity.getCoupangMonthPure() : 0;
        Long yogiyo = salesMonthPureEntity.getYogiyoMonthPure() != 0 ? salesMonthEntity.getYogiyoMonth() - salesMonthPureEntity.getYogiyoMonthPure() : 0;
        Long naver = salesMonthPureEntity.getNaverMonthPure() != 0 ? salesMonthEntity.getNaverMonth() - salesMonthPureEntity.getNaverMonthPure() : 0;
        Long tanyo = salesMonthPureEntity.getTanyoMonthPure() != 0 ? salesMonthEntity.getTanyoMonth() - salesMonthPureEntity.getTanyoMonthPure() : 0;
        Long total = card + simple + beamin + coupang + yogiyo + naver + tanyo;
        return FeesDTO.builder()
                .month(salesMonthEntity.getMonth())
                .cardFee(card)
                .simpleFee(simple)
                .baeminFee(beamin)
                .coupangFee(coupang)
                .yogiyoFee(yogiyo)
                .naverFee(naver)
                .tanyoFee(tanyo)
                .totalFee(total)
                .build();
    }
}

