package com.itacademy.repository.impl;

import com.itacademy.exception.NotSuchElementException;
import com.itacademy.model.User;
import com.itacademy.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;


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
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.persist(user);
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            throw new UnsupportedOperationException(e.getConstraintViolations().stream().map(ConstraintViolation::getMessageTemplate).collect(Collectors.joining(", ")));
        }catch (PersistenceException e) {
            throw new UnsupportedOperationException(e.getMessage());
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
            } else throw new NotSuchElementException("There are not user with id " + id);
    }

    @Override
    public User update(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.update(user);
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            throw new UnsupportedOperationException(e.getConstraintViolations().stream().map(ConstraintViolation::getMessageTemplate).collect(Collectors.joining(", ")));
        }catch (PersistenceException e) {
            throw new UnsupportedOperationException();
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
}
