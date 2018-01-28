package com.marketingsuite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/confim")
    public String getConfimationPage(){
        return "confirm";
    }


}
