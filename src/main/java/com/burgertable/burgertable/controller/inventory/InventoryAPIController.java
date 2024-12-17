package com.burgertable.burgertable.controller.inventory;

import com.burgertable.burgertable.dto.inventory.InventoryDTO;
import com.burgertable.burgertable.service.inventory.InventoryAddService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryAPIController {
    private final InventoryAddService inventoryAddService;

    @PostMapping("/lowStockAdd")
    public ResponseEntity<?> lowStockAdd(@RequestBody InventoryDTO inventoryDTO) {
        log.info("inventoryDTO: {}", inventoryDTO.toString());
        if (inventoryAddService.lowStockAdd(inventoryDTO.getId(), inventoryDTO.getLowStock())){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("임계값 설정 오류");
    }





}
