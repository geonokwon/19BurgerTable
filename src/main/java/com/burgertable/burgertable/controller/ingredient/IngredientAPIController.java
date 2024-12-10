package com.burgertable.burgertable.controller.ingredient;

import com.burgertable.burgertable.dto.ingredient.IngredientDTO;
import com.burgertable.burgertable.service.ingredient.IngredientAddService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientAPIController {

    private final IngredientAddService ingredientAddService;

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> ingredientAdd(@RequestBody IngredientDTO ingredientDTO){
        if (ingredientAddService.add(ingredientDTO)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("추가 오류");
    }
}
