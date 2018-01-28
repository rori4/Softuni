package com.example.demo.controllers;

import com.example.demo.models.AddGiveawayModel;
import com.example.demo.services.GiveawayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/giveaways")
public class GiveawayController {
    @Autowired
    private GiveawayService giveawayService;

    @GetMapping("add")
    public String getAddGiveawayPage(Model model){
        AddGiveawayModel addGiveawayModel = new AddGiveawayModel();
        model.addAttribute("giveaway", addGiveawayModel);
        model.addAttribute("type","Add");
        return "giveaway";
    }

    @PostMapping("add")
    public String addGiveaway(@ModelAttribute AddGiveawayModel addGiveawayModel){
        this.giveawayService.save(addGiveawayModel);
        return "redirect:/giveaways/add";
    }
}
