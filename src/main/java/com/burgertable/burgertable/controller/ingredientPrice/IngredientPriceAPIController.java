package com.burgertable.burgertable.controller.ingredientPrice;

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

    @PostMapping("/getIngredientNames")
    public ResponseEntity<?> getIngredientNames(@RequestBody String category){
        log.info("category: {}", category);
        System.out.println(category);
        List<String> getIngredientNames = ingredientPriceGetService.getIngredientNames(category);
        if (getIngredientNames != null){
            log.info("getIngredientNames {}", getIngredientNames);
            return ResponseEntity.ok().body(getIngredientNames);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("카테고리별 불러오기 오류");
    }


}
