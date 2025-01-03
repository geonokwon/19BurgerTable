package com.burgertable.burgertable.service.sales;

import com.burgertable.burgertable.dto.sales.SalesLogDataDTO;
import com.burgertable.burgertable.dto.sales.SalesLogPageDTO;
import com.burgertable.burgertable.dto.sales.SalesSaveDataDTO;
import com.burgertable.burgertable.entity.SalesEntity;
import com.burgertable.burgertable.mapper.sales.SalesLogMapper;
import com.burgertable.burgertable.mapper.sales.SalesMapper;
import com.burgertable.burgertable.repository.sales.SalesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalesLogService {
    private final SalesRepository salesRepository;

    //매출 리스트 화면 뿌려줄 PageList
    public SalesLogPageDTO getSalesPaged(int page, int pageSize) {
        //pageable 객체 생성
        Pageable pageable = PageRequest.of(page, pageSize);
        //생성일자 내림차순으로 정렬 후 페이징
        Page<SalesEntity> salesEntityPage = salesRepository.findAllByIsDeletedFalseOrderBySalesDateDesc(pageable);
        return SalesLogMapper.INSTANCE.toSalesLogPageDTO(salesEntityPage);
    }

    //매출 클릭시 모달형태의 상세보기 값 을 넘겨받기 위한 서비스
    public Map<String, Object> getSalesData(Long salesPK){
        Optional<SalesEntity> result = salesRepository.findById(salesPK);
        if (result.isPresent()){
            SalesEntity salesEntity = result.get();
            //매핑
            SalesSaveDataDTO salesSaveDataDTO = SalesMapper.INSTANCE.toSalesSaveDataDTO(salesEntity);
            log.info("salesSaveDataDTO {}", salesSaveDataDTO.toString());
            salesSaveDataDTO.setUserName(salesEntity.getUser().getName());
            return Map.of("response", "success",
                    "result", salesSaveDataDTO);
        }
        return Map.of("response", "fail");
    }
}
