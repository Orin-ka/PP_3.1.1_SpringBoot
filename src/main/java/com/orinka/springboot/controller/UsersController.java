package com.orinka.springboot.controller;

import com.orinka.springboot.entity.User;
import com.orinka.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/users")
public class UsersController {

    private final UserService userService;

    //Спринг внедряет через конструктор зависимость от userService
    //@Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String allUsers(ModelMap model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "index";
    }

    @GetMapping(value = "/{id}")
    public String getUser(@PathVariable("id")Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

    @GetMapping(value = "/new")
    public  String newUser (Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping(value ="/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(user, id);
        return "redirect:/users";
    }

    @DeleteMapping(value = "/{id}")
    public String delete (@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
