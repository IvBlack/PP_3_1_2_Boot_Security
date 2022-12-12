package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.MyUser;

import java.util.List;

public interface UserService {

    public List<MyUser> getAllUsers();

    public MyUser getUser(int id);
    public void updateUser(int id, MyUser myUser);

    public void saveUser(MyUser myUser);
    public void deleteUser(int id);
}