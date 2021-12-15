package ru.beljankin.scurityboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.beljankin.scurityboot.entities.User;
import ru.beljankin.scurityboot.services.RoleServise;
import ru.beljankin.scurityboot.services.UserServiceImplDS;
import ru.beljankin.scurityboot.services.UserServise;


@Controller
@RequestMapping("/user")
public class UserController {
    private final UserServise personServise;
    private final RoleServise roleServise;
    private final UserServiceImplDS userDetailsServiceIpml;

    @Autowired
    public UserController(UserServise personServise, RoleServise roleServise,
                          UserServiceImplDS userDetailsServiceIpml){
        this.personServise = personServise;
        this.roleServise = roleServise;
        this.userDetailsServiceIpml = userDetailsServiceIpml;
    }

    @GetMapping("/{id}")
    public String userPage(Model model, @PathVariable("id") int id){
        User person = personServise.select(id);
        model.addAttribute("user",personServise.select(id));
        return "user/index";
    }
}
