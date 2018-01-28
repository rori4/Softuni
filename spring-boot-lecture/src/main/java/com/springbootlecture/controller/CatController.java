package com.springbootlecture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cat")
public class CatController {

    @GetMapping("")
    public String getCatHomePage(){
        return "cat-home-page.html";
    }

    @PostMapping("")
    public String buyCat(@RequestParam String name,@RequestParam int age ){
        System.out.println("Name:" + name);
        System.out.println("Age:" + age);

        return "redirect:/dog";
    }
}
