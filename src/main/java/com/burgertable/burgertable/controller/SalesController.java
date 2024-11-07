package com.burgertable.burgertable.controller;

import com.burgertable.burgertable.dto.SalesLogPageDTO;
import com.burgertable.burgertable.dto.SalesMonthDataDTO;
import com.burgertable.burgertable.service.sales.SalesLogService;
import com.burgertable.burgertable.service.sales.SalesMonthService;
import com.burgertable.burgertable.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SalesController {

    private final SalesLogService salesLogService;
    private final SalesMonthService salesMonthService;

    private final int PAGE_SIZE = 20;

    @GetMapping("/salesLog")
    public String salesLog(@RequestParam(defaultValue = "0") int page, // 현재 페이지
                           Model model) {
        //월별 매출 표기 처음 페이지로 들어오면 현재 날자 기준으로 월별 데이터 뿌려주기 위함!
        SalesMonthDataDTO salesMonthDataDTO = salesMonthService.getSalesMonthData();
        model.addAttribute("salesMonthDataDTO", salesMonthDataDTO);

        //페이징처리 및 데이터 불러오기
        SalesLogPageDTO salesLogPageDTO = salesLogService.getSalesPaged(page, PAGE_SIZE);

        model.addAttribute("salesLogList", salesLogPageDTO.getSalesLogData());
        model.addAttribute("salesLogPageDTO", salesLogPageDTO);
        return "sales/sales-log";
    }

    @GetMapping("/salesInput")
    public String salesInput(Model model) {
        //로그인한 사용자 이름 불러와서 글 작성자 란에 추가하기 위한 model
        model.addAttribute("userName", SecurityUtil.getUserName());
        return "sales/sales-input";
    }
}
