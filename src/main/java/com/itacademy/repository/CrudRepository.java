package com.itacademy.repository;


import java.util.List;

public interface CrudRepository<T, ID>{

    T save(T t);
    void delete(ID id);
    T update(T t);
    T getById(ID id);
    List<T> getAll();
}
