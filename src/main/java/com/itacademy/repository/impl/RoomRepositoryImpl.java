package com.itacademy.repository.impl;

import com.itacademy.model.Room;
import com.itacademy.model.RoomCompositeId;
import com.itacademy.repository.RoomRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RoomRepositoryImpl implements RoomRepository {

    private RepositoryUtilityClass utilityClass;

    public RoomRepositoryImpl(RepositoryUtilityClass utilityClass) {
        this.utilityClass = utilityClass;
    }

    @Override
    public Room save(Room room) {
        return utilityClass.saveOperationWithinPersistentContext(
                session ->{
                    session.persist(room);
                    return room;
                }
        );
    }

    @Override
    public void delete(RoomCompositeId id) {
        utilityClass.deleteOperationWithinPersistentContext(
                session -> {
                        session.delete(getById(id));
                }
        );
    }

    @Override
    public Room update(Room room) {
        return utilityClass.updateOperationWithinPersistentContext(
                session -> {
                    Room roomFromDb=session.get(Room.class,room.getId());
                    roomFromDb.setType(room.getType());
                    roomFromDb.setPrice(room.getPrice());
                    roomFromDb.setPicture_url(room.getPicture_url());

                    session.saveOrUpdate(roomFromDb);
                    return room;
                }
        );
    }

    @Override
    public Room getById(RoomCompositeId id) {
        return utilityClass.getByIdOperationWithinPersistentContext(
                session ->
                        session.get(Room.class,id)
        );
    }

    @Override
    public List<Room> getAll() {
        return utilityClass.getAllOperationWithinPersistentContext(
                session -> {
                    Query query=session.createQuery("select room from Room room");
                    return query.list();
                }
        );
    }

    @Override
    public List<Room> getAllRoomsInHotel(String hotelName) {
//        return utilityClass.getAllOperationWithinPersistentContext(
//                session -> {
//                    Query query=session.createQuery("select room from Room as room " +
//                            " join hotel as hotel where hotel.name= :hotelName ");
//                    query.setParameter("hotelName",hotelName);
//                    return query.list();
//                }
//        );
        throw new UnsupportedOperationException("Method is not implemented ");
    }

    @Override
    public List<Room> getAllRoomsInHotelById(Long hotel_id) {
        return utilityClass.getAllOperationWithinPersistentContext(
                session -> {
                    Query query=session.createQuery("select room from Room room where id.hotel.id= :hotelId ");
                    query.setParameter("hotelId", hotel_id );
                    return query.list();
                }
        );

    }

}
