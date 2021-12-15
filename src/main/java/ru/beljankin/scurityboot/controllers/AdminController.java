package ru.beljankin.scurityboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.beljankin.scurityboot.entities.Role;
import ru.beljankin.scurityboot.entities.User;
import ru.beljankin.scurityboot.services.RoleServise;
import ru.beljankin.scurityboot.services.UserServise;


import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServise personServise;
    private final RoleServise roleServise;

    @Autowired
    public AdminController(UserServise personServise, RoleServise roleServise){
        this.personServise = personServise;
        this.roleServise = roleServise;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personServise.getAll());
        return "admin/users";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") User person
            , @RequestParam("rolesNames") String[] rolesNames){

        Set<Role> roleSet = new HashSet<>();
        if(rolesNames.length !=0){
            for (String role: rolesNames) {
                roleSet.add(roleServise.findRoleByString(role));
            }
        } else {
            roleSet.add(roleServise.findRoleByString("ROLE_USER"));
        }
        person.setRoles(roleSet);
        personServise.save(person);
        return "redirect:/admin";
    }

    @GetMapping("new-person")
    public String newPerson(@ModelAttribute("person") User person){
        return "admin/new-person";
    }

//
    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("person") User person, @PathVariable("id") int id,
                         @RequestParam("rolesNames") String[] rolesNames) {
        Set<Role> roleSet = new HashSet<>();
        if(rolesNames.length !=0){
            for (String role: rolesNames) {
                roleSet.add(roleServise.findRoleByString(role));
            }
        } else {
            roleSet.add(roleServise.findRoleByString("ROLE_USER"));
        }
        person.setRoles(roleSet);
        personServise.update(id, person);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personServise.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personServise.select(id));
        return "admin/edit";
    }


    }
