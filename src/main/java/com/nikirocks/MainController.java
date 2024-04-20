package com.nikirocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    private Menu menu;

    @GetMapping
    @RequestMapping("/")
    public String index() {
        menu.setHome(true);
        return "index";
    }

    @GetMapping
    @RequestMapping("/secured")
    public String secured(Model model) {
        menu.setSecured(true);
        model.addAttribute("secured", true);
        return "index";
    }

}
