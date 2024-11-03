package com.burgertable.burgertable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/salesLog")
    public String salesLog() {
        return "/sales-log";
    }
}
