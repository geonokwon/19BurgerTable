package com.burgertable.burgertable.controller;

import com.burgertable.burgertable.dto.SalesSaveDataDTO;
import com.burgertable.burgertable.service.sales.SalesLogService;
import com.burgertable.burgertable.service.sales.SaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

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
        log.info("save data: {}", salesSaveDataDTO);
        if (saveService.save(salesSaveDataDTO)){
            return Map.of("result", "success");
        }
        return Map.of("result", "fail");
    }

    @PostMapping("/getSalesLog")
    @ResponseBody
    public Map<String, Object> getSalesLog(@RequestBody Long salesPK) {
        return salesLogService.getSalesData(salesPK);
    }
}
