package com.marketingsuite.controllers;

import com.marketingsuite.errors.Errors;
import com.marketingsuite.models.RegistrationModel;
import com.marketingsuite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/app")
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model){
        if(error != null){
            model.addAttribute("error", Errors.INVALID_CREDENTIALS);
        }
        model.addAttribute("form","login");
        return "auth";
    }

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute RegistrationModel registrationModel, Model model){
        model.addAttribute("form","register");
        return "auth";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegistrationModel registrationModel, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("form","register");
            return "auth";
        }
        this.userService.register(registrationModel);
        return "redirect:/confirm";
    }
}
