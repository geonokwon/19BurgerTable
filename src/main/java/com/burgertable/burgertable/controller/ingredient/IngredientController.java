package com.burgertable.burgertable.controller.ingredient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    @GetMapping("/list")
    public String list() {
        return "ingredient/ingredient";
    }



}
