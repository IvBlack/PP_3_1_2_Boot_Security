package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.MyUser;
import ru.kata.spring.boot_security.demo.repo.UsersRepository;

import javax.transaction.Transactional;

@Service
public class RegistrationService {
    private final UsersRepository usersRepository;

    @Autowired
    public RegistrationService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    //сохраняем человека, который пришел из формы при регистрации
    @Transactional
    public void register(MyUser myUser) {
        myUser.setRole("ROLE_USER");
        usersRepository.save(myUser);
    }
}
