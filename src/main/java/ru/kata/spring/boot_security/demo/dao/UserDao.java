package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.MyUser;

import java.util.List;

public interface UserDao {

    public List<MyUser> getAllUsers();

    public MyUser getUser(int id);
    public void updateUser(int id, MyUser myUser);

    public void saveUser(MyUser customer);
    public void deleteUser(int id);
}
