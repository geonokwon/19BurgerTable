package com.burgertable.burgertable.service.sales;

import com.burgertable.burgertable.entity.SalesEntity;
import com.burgertable.burgertable.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesLogService {

    private final SalesRepository salesRepository;


    public void getSalesPaged(int page, int pageSize) {

        //pageable 객체 생성
        Pageable pageable = PageRequest.of(page, pageSize);
        //생성일자 내림차순으로 정렬 후 페이징
        Page<SalesEntity> salesEntityPage = salesRepository.findAllByOrderBySalesDateDesc(pageable);

    }
}
