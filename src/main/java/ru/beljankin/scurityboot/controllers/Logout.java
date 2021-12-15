package ru.beljankin.scurityboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Logout {
    @GetMapping(value = "/logoutsucsess")
    public String logout(){
        return "/logoutsucsess";
    }
}
