package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.MyUser;
import ru.kata.spring.boot_security.demo.service.RegistrationService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;
    private final UserValidator userValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, UserValidator userValidator) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/auth/login";
    }

    //@ModelAttribute положит в модель пустой объект класса MyUser
    @GetMapping("/registration")
    public  String registerPage(@ModelAttribute("myUser") MyUser myUser) {
        return "auth/registration";
    }

    //валидация данных юзера
    //а т.ж. наличие его в БД
    @PostMapping("/registration")
    public  String performRegistration(@ModelAttribute("myUser") @Valid MyUser myUser, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "/auth/registration";

        //показ ошибки, если она есть в полях ввода
        userValidator.validate(myUser, bindingResult);

        //сохраняем в БД после валидации
        registrationService.register(myUser);
        return "redirect:/auth/login";
    }

}
