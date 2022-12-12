package ru.kata.spring.boot_security.demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.MyUser;

import java.util.Collection;
import java.util.Collections;

//обертка над сущностью для стандартизации работы с методами UserDetails
//SS использует ее для работы с сущностью
public class MyUserDetails implements UserDetails {
    private final MyUser myUser;

    public MyUserDetails(MyUser myUser) {
        this.myUser = myUser;
    }

    //коллекция прав для конкретного юзера для доступа к опр. страницам
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority(myUser.getRole()));
    }

    //берем пароль юзера в обертке getPassword
    @Override
    public String getPassword() {
        return this.myUser.getPass();
    }

    @Override
    public String getUsername() {
        return this.myUser.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //возвращает юзера с его полями после аутен-ции
    public MyUser getMyUser() {
        return this.myUser;
    }
}

