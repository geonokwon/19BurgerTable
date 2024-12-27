package com.burgertable.burgertable.controller.ingredientPrice;

import com.burgertable.burgertable.dto.ingredientPrice.IngredientPriceDTO;
import com.burgertable.burgertable.service.IngredientPrice.IngredientPriceAddService;
import com.burgertable.burgertable.service.IngredientPrice.IngredientPriceGetService;
import com.burgertable.burgertable.service.IngredientPrice.IngredientPriceUpdateService;
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
    private final IngredientPriceUpdateService ingredientPriceUpdateService;

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

    @PostMapping("/get")
    public ResponseEntity<?> get(@RequestBody Long id){
        log.info("id {}", id);
        IngredientPriceDTO ingredientPriceDTO = ingredientPriceGetService.get(id);
        if (ingredientPriceDTO != null){
            return ResponseEntity.ok().body(ingredientPriceDTO);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("데이터 가져오기 실패");
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody IngredientPriceDTO ingredientPriceDTO){
        log.info("ingredientPriceDTO: {}", ingredientPriceDTO);
        boolean isValid = ingredientPriceUpdateService.update(ingredientPriceDTO);
        if (isValid){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("데이터 수정 실패");
    }


}
