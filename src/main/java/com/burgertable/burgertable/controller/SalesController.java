package com.burgertable.burgertable.controller;

import com.burgertable.burgertable.entity.SalesEntity;
import com.burgertable.burgertable.service.sales.SalesLogService;
import com.burgertable.burgertable.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SalesController {

    private final SalesLogService salesLogService;
    private final int PAGE_SIZE = 20;

    @GetMapping("/salesLog")
    public String salesLog(@RequestParam(defaultValue = "0") int page, // 현재 페이지
                           Model model) {
        //페이징처리
        salesLogService.getSalesPaged(page,PAGE_SIZE);
        return "sales/sales-log";
    }

    @GetMapping("/salesInput")
    public String salesInput(Model model) {
        //로그인한 사용자 이름 불러와서 글 작성자 란에 추가하기 위한 model
        model.addAttribute("userName", SecurityUtil.getUserName());
        return "sales/sales-input";
    }
}
