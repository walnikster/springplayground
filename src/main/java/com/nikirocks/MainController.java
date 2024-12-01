package com.nikirocks;

import com.nikirocks.user.UserDao;
import com.nikirocks.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    private UserDao userDao;
    private Menu menu;

    @Autowired
    public MainController(UserDao userDao, Menu menu) {
        this.userDao = userDao;
        this.menu = menu;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        menu.setHome(true);
        return "index";
    }

    @RequestMapping(value = "/secured", method = RequestMethod.GET)
    public String secured(Model model) {
        menu.setSecured(true);
        Iterable<UserEntity> all = userDao.findAll();
        model.addAttribute("userlogins", all);
        return "index";
    }

}
