package com.itacademy.repository;

import com.itacademy.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepositoryImpl implements UserRepository{


    SessionFactory sessionFactory;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public User save(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
        return user;
    }
}
