package com.itacademy.repository.impl;

import com.itacademy.exception.RepositoryException;
import com.itacademy.model.Hotel;
import com.itacademy.repository.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Slf4j
public class HotelRepositoryImpl implements HotelRepository {

    SessionFactory sessionFactory;

    public HotelRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Hotel getById(Long id) {
        Session session=null;
        Hotel hotel=null;
        try{
            session=sessionFactory.openSession();
            hotel=session.get(Hotel.class,id);
        }catch (Exception e){
            throw new RepositoryException("getById method failed at getById() ");
        }finally {
            session.close();
        }
        return hotel    ;
    }


    @Override
    public Hotel save(Hotel hotel) {
        log.info("====save hotel method begins=====");
        Session session = sessionFactory.openSession();
        try{
            session.getTransaction().begin();
            session.persist(hotel);
            session.getTransaction().commit();
            log.info("=====save hotel method end successfully=====");
        }catch (Exception e){
            session.getTransaction().rollback();
            log.info("=====save hotel method failed====");
            throw new RepositoryException("persistent layer failed at save() method");
        }finally {
            if(session != null) {
                session.close();
            }
        }

        return hotel;
    }

    @Override
    public void delete(Long id) {
        Session session=null;
        Hotel hotel=null;
        try{
            session = sessionFactory.openSession();
            hotel=getById(id);
            session.getTransaction().begin();
            session.delete(hotel);
            session.getTransaction().commit();
            log.info("=====save hotel method end successfully=====");
        }catch (Exception e){
            session.getTransaction().rollback();
            log.info("=====save hotel method failed====");
            throw new RepositoryException("persistent layer failed at delete() method");
        }finally {
            if(session != null) {
                session.close();
            }
        }
    }

    @Override
    public Hotel update(Hotel hotel) {
        Session session=null;
        Hotel hotelFromDb=null;
        try{
            session = sessionFactory.openSession();
            hotelFromDb=getById(hotel.getId());
            session.getTransaction().begin();

            hotelFromDb.setName(hotel.getName());
            hotelFromDb.setStars(hotel.getStars());
            hotelFromDb.setRooms(hotel.getRooms());

            session.getTransaction().commit();
            log.info("=====update hotelFromDb method end successfully=====");
        }catch (Exception e){
            session.getTransaction().rollback();
            log.info("=====update hotelFromDb method failed====");
            throw new RepositoryException("persistent layer failed at delete() method");
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return hotelFromDb;
    }

    @Override
    public List<Hotel> getAll() {
        Session session=null;
        List<Hotel> hotels=null;
        try{
            session = sessionFactory.openSession();
            Query query=session.createQuery("select hotel from Hotel hotel");
            hotels=query.list();

        }catch (Exception e){

            throw new RepositoryException("persistent layer failed at getAll() method");
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return hotels;
    }
}