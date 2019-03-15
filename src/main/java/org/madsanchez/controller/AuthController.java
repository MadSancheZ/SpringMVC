package org.madsanchez.controller;

import org.madsanchez.model.User;
import org.madsanchez.service.UserService;
import org.madsanchez.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/auth/sign_up")
    public String setUpUser(Model model){
        model.addAttribute("user", new User());
        return "/auth/sign_up";
    }

    @PostMapping("/auth/sign_up")
    public String signUp(@ModelAttribute @Valid User user, BindingResult result) {
        userValidator.validate(user, result);
        if(result.hasErrors()){
            return "auth/sign_up";
        }
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/login")
    public String login(@RequestParam (name = "error", required = false) boolean error,
                        Model model){
        if(Boolean.TRUE.equals(error)){
            model.addAttribute("error", true);
        }
        return "/auth/sign_in";
    }
}
