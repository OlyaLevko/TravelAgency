package com.itacademy.repository.impl;

import com.itacademy.exception.NotSuchElementException;
import com.itacademy.model.Order;
import com.itacademy.model.User;
import com.itacademy.repository.OrderRepository;
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
public class OrderRepositoryImpl implements OrderRepository {
    SessionFactory sessionFactory;

    @Autowired
    public OrderRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order save(Order order) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.persist(order);
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            throw new UnsupportedOperationException(e.getConstraintViolations().stream().map(ConstraintViolation::getMessageTemplate).collect(Collectors.joining(", ")));
        }catch (PersistenceException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return order;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Order order = session.get(Order.class, id);
        if(order != null) {
            session.delete(order);
            session.getTransaction().commit();
            session.close();
        } else throw new NotSuchElementException("There are not user with id " + id);
    }

    @Override
    public Order update(Order order) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.saveOrUpdate(order);
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            throw new UnsupportedOperationException(e.getConstraintViolations().stream().map(ConstraintViolation::getMessageTemplate).collect(Collectors.joining(", ")));
        }catch (PersistenceException e) {
            throw new UnsupportedOperationException(e.getLocalizedMessage());
        }
        return order;
    }

    @Override
    public Order getById(Long id) {
        Session session = sessionFactory.openSession();
        Order order = session.get(Order.class, id);
        session.close();
        return order;
    }

    @Override
    public List<Order> getAll() {
        Session session = sessionFactory.openSession();
        List<Order> orders = session.createNativeQuery("select * from orders", Order.class).getResultList();
        session.close();
        return orders;
    }

    @Override
    public List<Order> getByUserId(Long id) {
        Session session = sessionFactory.openSession();
        List<Order> orders = session.createNativeQuery("select * from orders where user_id = " + id, Order.class).getResultList();
        session.close();
        return orders;
    }
}
