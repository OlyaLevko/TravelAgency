package com.itacademy.repository.impl;

import com.itacademy.exception.RepositoryException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@Component
public class RepositoryUtilityClass {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositoryUtilityClass(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public <T> T getByIdOperationWithinPersistentContext(Function<Session,T> function ){
        Session session = sessionFactory.openSession();
        T result=null;
        try{
            session=sessionFactory.openSession();

            result=function.apply(session);
            if(result==null){
                log.info("===== entity with id: .... not found =====");
            }
        }catch (Exception e){
            session.getTransaction().rollback();
            log.info("=====getById  method failed====");
            log.info(e.getMessage());
            throw new RepositoryException("persistent layer failed at getById() method");
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return result;
    }

    public <T> T getByNameOperationWithinPersistentContext(Function<Session,T> function ){
        Session session = sessionFactory.openSession();
        T result=null;
        try{
            session=sessionFactory.openSession();

            result=function.apply(session);
            if(result==null){
                log.info("===== entity with name: .... not found =====");
            }
        }catch (Exception e){
            session.getTransaction().rollback();
            log.info("=====getByName  method failed====");
            log.info(e.getMessage());
            throw new RepositoryException("persistent layer failed at getByName() method");
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return result;
    }

    public <T> T saveOperationWithinPersistentContext(Function<Session,T> function ){
        Session session = sessionFactory.openSession();
        T result=null;
        try{
            session.getTransaction().begin();

            result=function.apply(session);

            session.getTransaction().commit();
            log.info("=====save  method end successfully=====");
        }catch (Exception e){
            session.getTransaction().rollback();
            log.info("=====save  method failed====");
            log.info(e.getMessage());
            throw new RepositoryException("persistent layer failed at save() method");
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return result;
    }

    public <T> void deleteOperationWithinPersistentContext(Consumer<Session> function){
        Session session = sessionFactory.openSession();
        T entity=null;
        try{
            session.getTransaction().begin();

            function.accept(session);

            session.getTransaction().commit();
            log.info("=====delete  method end successfully=====");
        }catch (Exception e){
            session.getTransaction().rollback();
            log.info("=====delete  method failed====");
            log.info(e.getMessage());
            throw new RepositoryException("persistent layer failed at delete() method");
        }finally {
            if(session != null) {
                session.close();
            }
        }
    }
    public <T> T updateOperationWithinPersistentContext(Function<Session,T> function ){

        Session session = sessionFactory.openSession();
        T result=null;
        try{
            session.getTransaction().begin();

            result=function.apply(session);

            session.getTransaction().commit();
            log.info("===== update  method end successfully=====");
        }catch (Exception e){
            session.getTransaction().rollback();
            log.info("===== update  method failed====");
            log.info(e.getMessage());
            throw new RepositoryException("persistent layer failed at update() method");
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return result;
    }
    public <T> T getAllOperationWithinPersistentContext(Function<Session,T> function ){
        Session session = sessionFactory.openSession();
        T result=null;
        try{
            session=sessionFactory.openSession();

            result=function.apply(session);
            if(result==null){
                log.info("===== result list is null =====");
            }
        }catch (Exception e){
            session.getTransaction().rollback();
            log.info("=====getAll  method failed====");
            log.info(e.getMessage());
            throw new RepositoryException("persistent layer failed at getAll() method");
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return result;
    }

}
