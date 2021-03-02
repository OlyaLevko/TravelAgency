package com.itacademy.repository.impl;

import com.itacademy.model.User;
import com.itacademy.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {


    SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public User save(User user) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
            session.persist(user);
            session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    @Transactional
    public User update(User user) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public User getById(Long aLong) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
