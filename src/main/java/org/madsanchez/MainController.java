package org.madsanchez;


import org.madsanchez.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController {
    static List<User> users = new ArrayList<>();


    @GetMapping("/view/{name}")
    public String view(@PathVariable("name") String name, Model model){
        model.addAttribute("msg", "Hello "+name);
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model){

        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/new")
    public String setUpUser(){
        return "/sign_up";
    }

    @PostMapping("/users/new")
    public String signUp(@ModelAttribute User user){
        users.add(user);
        return "redirect:/users";
    }
}
