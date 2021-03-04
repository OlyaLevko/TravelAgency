package com.itacademy.repository.impl;

import com.itacademy.model.Room;
import com.itacademy.repository.RoomRepository;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

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
    public void delete(Long id) {
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
//                    roomFromDb.setOrders(room.getOrders());  todo figure out logic how to set orders
                    return room;
                }
        );
    }

    @Override
    public Room getById(Long id) {
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
        return utilityClass.getAllOperationWithinPersistentContext(
                session -> {
                    Query query=session.createQuery("select room from Room as room " +
                            " join room.hotel as hotel where hotel.name= :hotelName ");
                    query.setParameter("hotelName",hotelName);
                    return query.list();
                }
        );
    }

    @Override
    public List<Room> getAllRoomsInHotelById(Long id) {
        return utilityClass.getAllOperationWithinPersistentContext(
                session -> {
                    Query query=session.createQuery("select room from Room room where hotel.id= :hotelId ");
                    query.setParameter("hotelId", id );
                    return query.list();
                }
        );
    }
}
