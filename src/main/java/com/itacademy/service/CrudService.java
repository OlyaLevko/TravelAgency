package com.itacademy.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CrudService<T, ID> {
    T create(T t);
    void delete(ID id);
    T update(T t);
    T getById(ID id);
    List<T> getAll();
}
