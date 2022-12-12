package ru.kata.spring.boot_security.demo.config;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //убрали кастомную логику аутен-ции, внедряем MyUserDetailService
    private  final MyUserDetailsService myUserDetailsService;

    public WebSecurityConfig(MyUserDetailsService myUserDetailService) {
        this.myUserDetailsService = myUserDetailService;
    }

    //Параметры аутен-ции через внедрение auth-провайдера
    //указываем Security, какой провайдер используем
    protected void configure(@NotNull AuthenticationManagerBuilder auth) throws Exception {

        //провайдер отключен, передаем методу userDetailsService напрямую
        //spring сам получит юзера, сам проверит логин/пароль
        auth.userDetailsService(myUserDetailsService);
    }

    //конфиг авторизации по цепочке
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //настройка авторизации
        http.authorizeRequests()
                //авторизация только для админов
                .antMatchers("/admin").hasRole("ADMIN")
                //любой неаутентиф-ный юзер может зарегистрироваться/войти
                .antMatchers("/auth/login", "/auth/registration").permitAll()
                .anyRequest().hasAnyRole("USER", "ADMIN")
                .and()
                //настройка аутентификации
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                //при успешной аутен-ции
                .defaultSuccessUrl("/hello", true)
                .failureUrl("/auth/login/?error")
                .and()
                //настройка logout
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login"); //если успешно  разлогинился
    }


    @Bean
    PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}

