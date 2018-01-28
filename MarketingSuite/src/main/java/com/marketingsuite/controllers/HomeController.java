package com.marketingsuite.controllers;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage(){
        return "home";
    }

    @GetMapping("/unauthorized")
    public String getUnauthorizedPage(Model model){
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("authority", authorities);
        return "unauthorized";
    }
}
