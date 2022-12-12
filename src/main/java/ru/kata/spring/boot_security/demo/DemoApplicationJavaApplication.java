package ru.kata.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//запуск: main -> аутетификация ->страница hello
//logout - на странице hello
//2 юзера с ролями ROLE_USER, ROLE_ADMIN.
//страница админа - /admin.

//INSERT INTO `pp3-1-3`.`user` (`id`, `name`, `surname`, `department`, `salary`, `pass`, `role`) VALUES ('1', 'user', 'user', 'IT', '1000', 'user', 'ROLE_USER');
//INSERT INTO `pp3-1-3`.`user` (`id`, `name`, `surname`, `department`, `salary`, `pass`, `role`) VALUES ('2', 'admin', 'admin', 'IT', '2000', 'admin', 'ROLE_ADMIN');


@SpringBootApplication
public class DemoApplicationJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplicationJavaApplication.class, args);
	}

}
