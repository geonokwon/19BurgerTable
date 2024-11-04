package com.burgertable.burgertable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SalesController {
    @GetMapping("/salesLog")
    public String salesLog() {
        return "sales/sales-log";
    }

    @GetMapping("/salesInput")
    public String salesInput() {
        return "sales/sales-input";
    }


}
