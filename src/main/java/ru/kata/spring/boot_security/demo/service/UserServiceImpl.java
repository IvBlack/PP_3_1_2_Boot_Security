package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;

import java.util.List;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.MyUser;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    final private UserDao UserDao;

    @Autowired
    public UserServiceImpl(UserDao UserDao) {
        this.UserDao = UserDao;
    }

    @Override
    @Transactional
    public List<MyUser> getAllUsers() {
        return UserDao.getAllUsers();
    }

    @Override
    @Transactional
    public MyUser getUser(int id) {
        return UserDao.getUser(id);
    }

    @Override
    @Transactional
    public void updateUser(int id, MyUser myUser) {
        UserDao.updateUser(id, myUser);
    }

    @Override
    @Transactional
    public void saveUser(MyUser myUser) {
        UserDao.saveUser(myUser);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        UserDao.deleteUser(id);
    }
}


