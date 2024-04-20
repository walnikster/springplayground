package com.nikirocks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


    @GetMapping
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping
    @RequestMapping("/secured")
    public String secured(Model model) {
        model.addAttribute("secured", true);
        return "index";
    }

}
