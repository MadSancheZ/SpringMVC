package org.madsanchez;


import org.madsanchez.dao.UserDAO;
import org.madsanchez.model.User;
import org.madsanchez.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
public class MainController {


    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserValidator userValidator;


    @GetMapping("/view/{name}")
    public String view(@PathVariable("name") String name, Model model){
        model.addAttribute("msg", "Hello "+name);
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) throws SQLException {

        model.addAttribute("users", userDAO.getAll());
        return "users";
    }

    @GetMapping("/users/new")
    public String setUpUser(Model model){
        model.addAttribute("user", new User());
        return "/sign_up";
    }

    @PostMapping("/users/new")
    public String signUp(@ModelAttribute @Valid User user, BindingResult result) throws SQLException {
        userValidator.validate(user, result);
        if(result.hasErrors()){
            return "/sign_up";
        }
        userDAO.add(user);
        return "redirect:/users";
    }
}
