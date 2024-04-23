package com.nikirocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @Autowired
    private Menu menu;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        menu.setHome(true);
        return "index";
    }

    @RequestMapping(value = "/secured", method = RequestMethod.GET)
    public String secured(Model model) {
        menu.setSecured(true);
        return "index";
    }

}
