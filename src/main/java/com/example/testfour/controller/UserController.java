package com.example.testfour.controller;

import com.example.testfour.domain.User;
import com.example.testfour.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('USER')")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String userList(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "userList";
    }

    @PostMapping("/block/{id}")
    public String blockUsers(@PathVariable Long id){
        userService.block(id);
        return "redirect:" + "/users";
    }

    @PostMapping("/unblock/{id}")
    public String unblockUsers(@PathVariable Long id){
        userService.unblock(id);
        return "redirect:" + "/users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUsers(@PathVariable Long id){
        userService.delete(id);
        return "redirect:" + "/users";
    }

    @PostMapping("/blockAll")
    public String blockAll(@RequestParam String blockId){
        Set<Long> ids = getLongs(blockId);
        for (Long id : ids) {
            userService.block(id);
        }
        return "redirect:" + "/users";
    }

    @PostMapping("/unblockAll")
    public String unblockAll(@RequestParam String unblockId){
        Set<Long> ids = getLongs(unblockId);
        for (Long id : ids) {
            userService.unblock(id);
        }
        return "redirect:" + "/users";
    }

    @PostMapping("/deleteAll")
    public String deleteAll(@RequestParam String deleteId){
        Set<Long> ids = getLongs(deleteId);
        for (Long id : ids) {
            userService.delete(id);
        }
        return "redirect:" + "/users";
    }

    private Set<Long> getLongs(String selectedId) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(selectedId);
        Set<Long> ids = new HashSet<>();
        while (matcher.find()){
            ids.add(Long.valueOf(matcher.group()));
        }
        return ids;
    }


}
