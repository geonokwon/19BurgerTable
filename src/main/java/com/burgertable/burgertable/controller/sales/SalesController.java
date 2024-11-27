package com.burgertable.burgertable.controller.sales;

import com.burgertable.burgertable.dto.sales.FeesDTO;
import com.burgertable.burgertable.dto.sales.SalesLogPageDTO;
import com.burgertable.burgertable.dto.sales.SalesMonthDataDTO;
import com.burgertable.burgertable.service.sales.SalesLogService;
import com.burgertable.burgertable.service.sales.SalesMonthService;
import com.burgertable.burgertable.service.sales.SalesSaveService;
import com.burgertable.burgertable.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SalesController {

    private final SalesLogService salesLogService;
    private final SalesMonthService salesMonthService;

    private final int PAGE_SIZE = 20;
    private final SalesSaveService salesSaveService;

    //리스트 페이지
    @GetMapping("/salesLog")
    public String salesLog(@RequestParam(defaultValue = "0") int page, // 현재 페이지
                           @RequestParam(required = false) String yearMonth,
                           Model model) {

        // 유저가 선택한 날짜가 없으면 현재 날짜 사용
        YearMonth selectedYearMonth = (yearMonth != null) ? YearMonth.parse(yearMonth) : YearMonth.now();


        //월별 매출 표기 처음 페이지로 들어오면 현재 날자 기준으로 월별 데이터 전달
        SalesMonthDataDTO salesMonthDataDTO = salesMonthService.getSalesMonthData(selectedYearMonth);
        if (salesMonthDataDTO == null) {
            selectedYearMonth = YearMonth.now();
            salesMonthDataDTO = salesMonthService.getSalesMonthData(selectedYearMonth);
        }

        //salesMonthDataDTO가 여전히 null일 경우 기본값으로 초기화
        if (salesMonthDataDTO == null) {
            salesMonthDataDTO = new SalesMonthDataDTO(); // 빈 DTO 생성
        }

        model.addAttribute("selectedYearMonth", selectedYearMonth.toString()); // 선택된 값을 뷰로 전달
        model.addAttribute("salesMonthDataDTO", salesMonthDataDTO);

        // 월 순수익 계산
        Long totalMonth = salesMonthDataDTO.getTotalMonth() != null ? salesMonthDataDTO.getTotalMonth() : 0L;
        Long monthPureTotal = salesMonthDataDTO.getFees() != null
                ? totalMonth - salesMonthDataDTO.getFees().getTotalFee()
                : totalMonth; // fees가 null일 경우 totalMonth만 사용
        model.addAttribute("monthPureTotal", monthPureTotal);


        //페이징처리 및 데이터 불러오기
        SalesLogPageDTO salesLogPageDTO = salesLogService.getSalesPaged(page, PAGE_SIZE);

        model.addAttribute("salesLogList", salesLogPageDTO.getSalesLogData());
        model.addAttribute("salesLogPageDTO", salesLogPageDTO);
        return "sales/sales-log";
    }

    //글쓰기 페이지
    @GetMapping("/salesInput")
    public String salesInput(Model model) {
        //로그인한 사용자 이름 불러와서 글 작성자 란에 추가하기 위한 model
        model.addAttribute("userName", SecurityUtil.getUserName());
        return "sales/sales-input";
    }

    //글수정 페이지
    @GetMapping("/salesUpdate")
    public String salesUpdate(@RequestParam("pageNum")Long pageNum, Model model) {
        Map<String, Object> salesDataMap = salesLogService.getSalesData(pageNum);
        if (salesDataMap.get("result") != null ){
            model.addAttribute("salesSaveDataDTO", salesDataMap.get("result"));
        }
        model.addAttribute("userName", SecurityUtil.getUserName());
        return "sales/sales-update";
    }







}
