package ru.kata.spring.boot_security.demo.dao;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.MyUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl(@Qualifier("entityManagerFactory") @NotNull EntityManagerFactory EntityFactory) {
        this.entityManager = EntityFactory.createEntityManager();
    }

    //все юзеры из БД
    @Override
    public List<MyUser> getAllUsers() {
        List<MyUser> allUsers = entityManager.createQuery("from MyUser", MyUser.class).getResultList();
        return allUsers;
    }

    @Override
    public MyUser getUser(int id) {
        TypedQuery<MyUser> query = entityManager.createQuery("from MyUser where id = :id", MyUser.class)
                .setParameter("id", id);
        return query.getSingleResult();
    }

    //поля для апдейта юзера через temporary
    //не обязательно все 4
    @Override
    public void updateUser(int id, MyUser user) {
        MyUser tmp = getUser(id);
        tmp.setName(user.getName());
        tmp.setSurName(user.getSurName());
        tmp.setDepartment(user.getDepartment());
        tmp.setSalary(user.getSalary());
    }

    @Override
    public void saveUser(MyUser user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(entityManager.find(MyUser.class, id));
    }
}

