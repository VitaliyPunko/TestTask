package com.example.testtask.dao;

import java.io.Serializable;
import java.util.List;

public interface CommonDao<T extends Serializable> {

    List<T> findAll();

    T findById(Integer id);

    Integer create(T entity);

    Integer update(T entity);

    Integer delete(Integer id);
}
