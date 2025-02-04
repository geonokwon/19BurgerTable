package com.burgertable.burgertable.controller.inventory;

import com.burgertable.burgertable.dto.inventory.InventoryPaginationDTO;
import com.burgertable.burgertable.service.inventory.InventoryGetService;
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
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    protected final int PAGE_SIZE = 30;

    private final InventoryGetService inventoryGetService;

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(required = false) String category ,
                       Model model){

        model.addAttribute("category", category);

        InventoryPaginationDTO inventoryPaginationDTO = inventoryGetService.getAll(page, PAGE_SIZE, category);
        PaginationUtil.addPaginationData(inventoryPaginationDTO);
        model.addAttribute("inventory", inventoryPaginationDTO);

        //카테고리 리스트
        model.addAttribute("categoryList", inventoryGetService.getCategoryList());




        return "inventory/inventory";
    }


}
