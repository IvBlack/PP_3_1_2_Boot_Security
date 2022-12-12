package ru.kata.spring.boot_security.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.MyUser;

import java.util.Optional;

//передается имя юзера
//Data JPA возвращает такого юзера, если он найден
@Repository
public interface UsersRepository extends JpaRepository<MyUser, Integer> {

    //метод вызывается через прослойку service
    Optional<MyUser> findByName(String username);
}
