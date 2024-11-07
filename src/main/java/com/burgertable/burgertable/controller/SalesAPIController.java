package com.burgertable.burgertable.controller;

import com.burgertable.burgertable.dto.SalesSaveDataDTO;
import com.burgertable.burgertable.service.sales.SalesLogService;
import com.burgertable.burgertable.service.sales.SaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SalesAPIController {
    private final SaveService saveService;
    private final SalesLogService salesLogService;

    @PostMapping("/save")
    @ResponseBody
    public Map<String, String> save(@RequestBody SalesSaveDataDTO salesSaveDataDTO) {
        //매출기록 저장
        log.info("save data: {}", salesSaveDataDTO);
        if (saveService.save(salesSaveDataDTO)){
            return Map.of("result", "success");
        }
        return Map.of("result", "fail");
    }

    @PostMapping("/getSalesLog")
    @ResponseBody
    public Map<String, Object> getSalesLog(@RequestBody Long salesPK) {
        //매출기록 리스트 반환
        return salesLogService.getSalesData(salesPK);
    }
}
