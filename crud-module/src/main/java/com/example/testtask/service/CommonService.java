package com.example.testtask.service;

import java.io.Serializable;
import java.util.List;

public interface CommonService<T extends Serializable> {

    List<T> findAll();

    T findById(Integer id);

    Integer create(T entity);

    Integer update(T entity);

    Integer delete(Integer id);
}
