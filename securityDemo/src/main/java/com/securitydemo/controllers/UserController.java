package com.securitydemo.controllers;

import com.securitydemo.config.Errors;
import com.securitydemo.model.RegistrationModel;
import com.securitydemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute RegistrationModel registratonModel){
        return "/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegistrationModel registrationModel, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "register";
        }
        this.userService.register(registrationModel);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model){
        if (error != null){
            model.addAttribute("error", Errors.INVALID_CREDENTIALS);
        }
        return "login";
    }

    @GetMapping("/user")
    public String getUserPage(@RequestParam(required = false) String error, Model model){
        this.userService.delete();
        return "user";
    }

    @GetMapping("/admin")
    public String getAdminPage(@RequestParam(required = false) String error, Model model){
        this.userService.delete();
        return "admin";
    }

    @GetMapping("/unauthorized")
    public String getAdminPage(Model model){
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("authority", authorities);
        return "unauthorized";
    }
}
