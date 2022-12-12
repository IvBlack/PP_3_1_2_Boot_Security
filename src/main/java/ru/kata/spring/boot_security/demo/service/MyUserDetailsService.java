package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.MyUser;
import ru.kata.spring.boot_security.demo.repo.UsersRepository;
import ru.kata.spring.boot_security.demo.security.MyUserDetails;

import java.util.Optional;

//сервис загружает пользователя по имени пользователя
@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Autowired
    public MyUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    //загружает юзера из репозитория, если он есть
    @Override
    public UserDetails loadUserByUsername(String x) throws UsernameNotFoundException {
        Optional<MyUser> user = usersRepository.findByName(x);

        //если юзер не найден - исключение выбросится на странице login
        if(user.isEmpty())
            throw new UsernameNotFoundException("No one user was found.");
        //если найден - возвращаем юзера в обертке
        return new MyUserDetails(user.get());
    }
}
