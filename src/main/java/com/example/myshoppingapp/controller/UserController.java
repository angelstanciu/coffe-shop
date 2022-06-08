package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.model.User;
import com.example.myshoppingapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signin")
    public String signIn() {
        return "signin";
    }

    @GetMapping("/register")
    public String addUser(ModelMap modelMap) {
        modelMap.addAttribute("newUser", new User());
        return "user-register-form";
    }

    @PostMapping("/register/save")
    public String saveUser(User user) {
        userService.save(user);
        return "redirect:/signin";
    }
}
