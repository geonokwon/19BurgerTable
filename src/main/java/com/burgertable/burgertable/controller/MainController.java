package com.burgertable.burgertable.controller;

import com.burgertable.burgertable.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {
    @GetMapping("/")
    public String index() {
        log.info("userName {}", SecurityUtil.getUserName());
        log.info("userRole {}", SecurityUtil.getUserRoles());
        return "index";
    }
}
