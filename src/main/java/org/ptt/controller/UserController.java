package org.ptt.controller;

import org.ptt.persistence.entity.User;
import org.ptt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/insert")
    public ModelAndView insertUser(HttpServletRequest request) {
        userService.saveUser(request);
        return listUsers();
    }

    @GetMapping("/edit")
    public ModelAndView editUser(@RequestParam(value = "id", required = true) Long id) {
        User user = userService.getUser(id);
        ModelAndView modelAndView = new ModelAndView("user/user-form");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateUser(HttpServletRequest request) {
        userService.updateUser(request);
        return listUsers();
    }

    @GetMapping("/delete")
    public ModelAndView deleteUser(@RequestParam(value = "id", required = true) Long id) {
        userService.deleteUser(id);
        return listUsers();
    }


}
