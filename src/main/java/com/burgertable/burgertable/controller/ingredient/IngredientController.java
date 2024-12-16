package com.burgertable.burgertable.controller.ingredient;

import com.burgertable.burgertable.dto.ingredient.IngredientPaginationDTO;
import com.burgertable.burgertable.service.ingredient.IngredientGetService;
import com.burgertable.burgertable.utils.PaginationUtil;
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
    protected final int PAGE_SIZE = 20;


    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(required = false) String category,
                       Model model) {

        IngredientPaginationDTO ingredientPaginationDTO = ingredientGetService.getList(category, page, PAGE_SIZE);
        PaginationUtil.addPaginationData(ingredientPaginationDTO);

        //필터 값 유지를 위한 model
        model.addAttribute("category", category);
        //전체 리스트 + PaginationData
        model.addAttribute("ingredient", ingredientPaginationDTO);
        //카테고리 리스트
        model.addAttribute("categoryList", ingredientGetService.getCategoryList());

        return "ingredient/ingredient";
    }



}
