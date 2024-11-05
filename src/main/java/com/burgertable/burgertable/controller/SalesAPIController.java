package com.burgertable.burgertable.controller;

import com.burgertable.burgertable.dto.SalesSaveDataDTO;
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

    @PostMapping("/save")
    @ResponseBody
    public Map<String, String> save(@RequestBody SalesSaveDataDTO salesSaveDataDTO) {
        log.info("save data: {}", salesSaveDataDTO);
        if (saveService.save(salesSaveDataDTO)){
            return Map.of("result", "success");
        }
        return Map.of("result", "fail");
    }

}
