package com.burgertable.burgertable.controller.ingredient;

import com.burgertable.burgertable.service.ingredient.IngredientGetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientGetService ingredientGetService;

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(required = false) String category,
                       Model model) {
        //전체 리스트
        model.addAttribute("ingredientList", ingredientGetService.getList(category));
        //카테고리 리스트
        model.addAttribute("categoryList", ingredientGetService.getCategoryList());
        return "ingredient/ingredient";
    }



}
