package com.example.testfour.controller;

import com.example.testfour.domain.User;
import com.example.testfour.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String createUserForm() {
        return "registration";
    }

    @PostMapping()
    public String createUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String email,
                             Model model) {
        if (username.isBlank()){
            model.addAttribute("usernameError", "Username is blank");
            return "registration";
        }
        User userFromDB = userService.findByUsername(username);
        if (userFromDB != null) {
            model.addAttribute("usernameError", "User exist");
            return "registration";
        }
        userService.create(username, password, email);
        return "redirect:" + "/";
    }
}
