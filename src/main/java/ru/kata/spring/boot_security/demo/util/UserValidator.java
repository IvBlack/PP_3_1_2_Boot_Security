package ru.kata.spring.boot_security.demo.util;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.model.MyUser;
import ru.kata.spring.boot_security.demo.service.MyUserDetailsService;

@Component
public class UserValidator implements Validator {
    private  final MyUserDetailsService myUserDetailsService;

    public UserValidator(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MyUser.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MyUser myUser = (MyUser)target;

        //если исключение выброшено - юзера нет в БД
        try{
            myUserDetailsService.loadUserByUsername(myUser.getName());
        } catch (UsernameNotFoundException ignored) {
            return; //юзер не найден
        }

        errors.rejectValue("name", "", "Такой человек есть в базе.");
    }
}
