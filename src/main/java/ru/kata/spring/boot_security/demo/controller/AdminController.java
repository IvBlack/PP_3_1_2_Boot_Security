package ru.kata.spring.boot_security.demo.controller;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.MyUser;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

@Autowired
private UserService userService;

public AdminController(UserService userService) {
    this.userService = userService;
}

    //всех юзеров из БД переберем в цикле во view 'all-users'
    @GetMapping(value = "")
    public String showAllUsers(Model mod) {
        List<MyUser> allUsers = userService.getAllUsers();
        mod.addAttribute("allUsers", allUsers);
        return "admin";
    }


    @PostMapping(value = "/", params = {"addUser"})
    public String addNewUser(@NotNull ModelMap mod) {
        userService.saveUser(new MyUser());
        mod.addAttribute("allUsers", userService.getAllUsers());
        return "admin";
    }

    @PatchMapping(value = "/edit/{id}")
    //передаем непустого работника из БД
    public String UpdateUser(@ModelAttribute("user") MyUser myUser,
                             @PathVariable("id") int id) {
        userService.updateUser(myUser.getId(), myUser);
        return "redirect:/admin";
    }

    @GetMapping(value = "/{id}/edit")
    //в параметре - работник с уже заполненными данными
    public String saveUser(@PathVariable("id") int id, Model mod) {
        mod.addAttribute("user", userService.getUser(id));
        return "user-info";
    }

    @RequestMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam("deleteRow") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}





