package com.burgertable.burgertable.controller.sales;

import com.burgertable.burgertable.dto.sales.FeesDTO;
import com.burgertable.burgertable.dto.sales.SalesMonthPureDTO;
import com.burgertable.burgertable.dto.sales.SalesSaveDataDTO;
import com.burgertable.burgertable.service.sales.SalesDeleteService;
import com.burgertable.burgertable.service.sales.SalesLogService;
import com.burgertable.burgertable.service.sales.SalesSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    private final SalesDeleteService salesDeleteService;

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

    //글삭제 처리 (isDeleted true)
    @PostMapping("/salesDelete")
    @ResponseBody
    public ResponseEntity<?> salesDelete(@RequestBody Long pageNum){
        if (pageNum == null) {
            return ResponseEntity.badRequest().body("잘못된 요청: pageNum 이 없습니다.");
        }
        if (salesDeleteService.salesDateDelete(pageNum)) {
            return ResponseEntity.ok(" 삭제 완료");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 오류");
    }



    
}
