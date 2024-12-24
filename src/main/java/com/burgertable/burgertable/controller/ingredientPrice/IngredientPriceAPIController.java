package com.burgertable.burgertable.controller.ingredientPrice;

import com.burgertable.burgertable.dto.ingredientPrice.IngredientPriceDTO;
import com.burgertable.burgertable.service.IngredientPrice.IngredientPriceAddService;
import com.burgertable.burgertable.service.IngredientPrice.IngredientPriceGetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ingredientPrice")
public class IngredientPriceAPIController {

    private final IngredientPriceGetService ingredientPriceGetService;
    private final IngredientPriceAddService ingredientPriceAddService;

    @PostMapping("/getIngredientNames")
    public ResponseEntity<?> getIngredientNames(@RequestBody String category){
        log.info("category: {}", category);
        List<String> getIngredientNames = ingredientPriceGetService.getIngredientNames(category);
        if (getIngredientNames != null){
            log.info("getIngredientNames {}", getIngredientNames);
            return ResponseEntity.ok().body(getIngredientNames);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("카테고리별 불러오기 오류");
    }

    @PostMapping("/getIngredientUnit")
    public ResponseEntity<?> getIngredientUnit(@RequestBody String ingredientName){
        log.info("ingredientName: {}", ingredientName);
        String getUnit = ingredientPriceGetService.getIngredientUnit(ingredientName);
        if (!getUnit.isEmpty()){
            log.info("getUnit {}", getUnit);
            return ResponseEntity.ok().body(getUnit);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("단위 불러오기 오류");
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody List<IngredientPriceDTO> ingredientPriceDTOs){
        log.info("ingredientPriceDTOs: {}", ingredientPriceDTOs);
        boolean success = ingredientPriceAddService.add(ingredientPriceDTOs);
        if (success){
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("저장 실패");
    }


}
