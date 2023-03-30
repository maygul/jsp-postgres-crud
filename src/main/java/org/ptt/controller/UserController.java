package org.ptt.controller;

import org.ptt.persistence.entity.User;
import org.ptt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView listUsers() {

        List<User> users = userService.listUsers();
        ModelAndView modelAndView = new ModelAndView("user/user-list");
        modelAndView.addObject("listUser", users);

        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView newUser() {
        ModelAndView modelAndView = new ModelAndView("user/user-form");
        return modelAndView;
    }
}
