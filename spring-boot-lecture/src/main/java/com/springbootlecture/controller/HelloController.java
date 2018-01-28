package com.springbootlecture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello/{id}")
    public String hello(Model model){
        model.addAttribute("id",5);
        return "block";
    }
}
