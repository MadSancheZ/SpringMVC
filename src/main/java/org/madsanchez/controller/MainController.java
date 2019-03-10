package org.madsanchez.controller;


import org.madsanchez.dao.UserDAO;
import org.madsanchez.model.User;
import org.madsanchez.service.UserService;
import org.madsanchez.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;


    @GetMapping("/view/{name}")
    public String view(@PathVariable("name") String name, Model model){
        model.addAttribute("msg", "Hello "+name);
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {

        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/users/new")
    public String setUpUser(Model model){
        model.addAttribute("user", new User());
        return "/sign_up";
    }

    @PostMapping("/users/new")
    public String signUp(@ModelAttribute @Valid User user, BindingResult result) {
        userValidator.validate(user, result);
        if(result.hasErrors()){
            return "/sign_up";
        }
        userService.add(user);
        return "redirect:/users";
    }
}
