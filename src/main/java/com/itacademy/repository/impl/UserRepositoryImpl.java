package com.itacademy.repository.impl;

import com.itacademy.exception.NotSuchElementException;
import com.itacademy.model.User;
import com.itacademy.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
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
    public User save(User user) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.persist(user);
            session.getTransaction().commit();
        }catch (PersistenceException e) {
            if (session != null)
            session.getTransaction().rollback();
            throw new UnsupportedOperationException(e.getCause().getCause().getLocalizedMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return user;
    }

    @Override
    public void delete(Long id) {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            User user = session.get(User.class, id);
            if(user != null) {
                session.delete(user);
                session.getTransaction().commit();
                session.close();
            } else {
                session.close();
                throw new NotSuchElementException("There are not user with id " + id);
            }
    }

    @Override
    public User update(User user) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.update(user);
            session.getTransaction().commit();
        }catch (PersistenceException e) {
            if (session != null)
                session.getTransaction().rollback();
            throw new UnsupportedOperationException(e.getCause().getCause().getLocalizedMessage());
        }finally {
            if (session != null)
                session.close();
        }
        return user;
    }

    @Override
    public User getById(Long id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public List<User> getAll() {
        Session session = sessionFactory.openSession();
        List<User> users = session.createNativeQuery("select * from users", User.class).getResultList();
        session.close();
        return users;
    }


    @Override
    public User getByEmail(String email) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select user from User user where user.email = :email");
        query.setParameter("email", email);
        User user = (User) query.getSingleResult();
        session.close();
        return user;
    }
}
