package com.burgertable.burgertable.controller.sales;

import com.burgertable.burgertable.dto.sales.FeesDTO;
import com.burgertable.burgertable.dto.sales.SalesMonthPureDTO;
import com.burgertable.burgertable.dto.sales.SalesSaveDataDTO;
import com.burgertable.burgertable.service.sales.SalesLogService;
import com.burgertable.burgertable.service.sales.SalesSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SalesAPIController {
    private final SalesSaveService salesSaveService;
    private final SalesLogService salesLogService;

    @PostMapping("/save")
    @ResponseBody
    public Map<String, String> save(@RequestBody SalesSaveDataDTO salesSaveDataDTO) {
        //매출기록 저장
        log.info("save data: {}", salesSaveDataDTO);
        if (salesSaveService.save(salesSaveDataDTO)){
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

    @PostMapping("/setMonthPure")
    @ResponseBody
    public ResponseEntity<?> setMonthPure(@RequestBody SalesMonthPureDTO salesMonthPureDTO){
        FeesDTO feesDTO = salesSaveService.setSalesMonthPureToFees(salesMonthPureDTO);
        if (feesDTO == null){
            return ResponseEntity.badRequest().body("Failed: FeesDTO is null");
        }
        return ResponseEntity.ok().body(feesDTO);

    }
}
