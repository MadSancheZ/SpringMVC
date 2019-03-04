package org.madsanchez;


import org.madsanchez.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/view/{name}")
    public String view(@PathVariable("name") String name, Model model){
        model.addAttribute("msg", "Hello "+name);
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model){
        Collection<User> users = new ArrayList<>();
        users.add(new User("Dic", "Grayson", "notBatman@bat.com"));
        users.add(new User("Bruce", "Wayne", "iamBatman@bat.com"));
        model.addAttribute("users", users);
        return "users";
    }
}
