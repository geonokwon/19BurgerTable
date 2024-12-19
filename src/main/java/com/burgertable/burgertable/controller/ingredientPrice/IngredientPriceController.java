package com.burgertable.burgertable.controller.ingredientPrice;

import com.burgertable.burgertable.dto.ingredientPrice.IngredientPricePaginationDTO;
import com.burgertable.burgertable.service.IngredientPrice.IngredientPriceGetService;
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
@RequestMapping("/ingredientPrice")
public class IngredientPriceController {
    protected final int PAGE_SIZE = 20;
    private final IngredientPriceGetService ingredientPriceGetService;


    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(required = false) String category ,
                       Model model) {

        model.addAttribute("category", category);

        IngredientPricePaginationDTO ingredientPricePaginationDTO = ingredientPriceGetService.getAll(category, page, PAGE_SIZE);
        PaginationUtil.addPaginationData(ingredientPricePaginationDTO);
        model.addAttribute("ingredientPrice", ingredientPricePaginationDTO);

        model.addAttribute("categoryList", ingredientPriceGetService.getCategoryList());


        return "ingredientPrice/ingredientPrice";
    }
}
