package com.burgertable.burgertable.controller.ingredient;

import com.burgertable.burgertable.dto.ingredient.IngredientDTO;
import com.burgertable.burgertable.service.ingredient.IngredientAddService;
import com.burgertable.burgertable.service.ingredient.IngredientDeleteService;
import com.burgertable.burgertable.service.ingredient.IngredientGetService;
import com.burgertable.burgertable.service.ingredient.IngredientUpdateService;
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
    private final IngredientGetService ingredientGetService;
    private final IngredientUpdateService ingredientUpdateService;
    private final IngredientDeleteService ingredientDeleteService;

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> ingredientAdd(@RequestBody IngredientDTO ingredientDTO){
        if (ingredientAddService.add(ingredientDTO)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("추가 오류");
    }

    @PostMapping("/get")
    @ResponseBody
    public ResponseEntity<?> ingredientGet(@RequestBody Long id){
        if (ingredientGetService.getIngredient(id) != null){
            return ResponseEntity.ok().body(ingredientGetService.getIngredient(id));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("요청 오류");
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<?> ingredientUpdate(@RequestBody IngredientDTO ingredientDTO){
        if (ingredientUpdateService.updateIngredient(ingredientDTO)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수정 오류");
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseEntity<?> ingredientDelete(@RequestBody Long id){
        if (ingredientDeleteService.deleteIngredient(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 오류");
    }

}
