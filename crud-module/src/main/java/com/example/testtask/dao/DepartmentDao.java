package com.example.testtask.dao;

import entity.DepartmentEntity;

import java.util.List;

public interface DepartmentDao {

    List<DepartmentEntity> findAll();

    DepartmentEntity findById(Integer id);

    Integer create(DepartmentEntity department);

    Integer update(DepartmentEntity department);

    Integer delete(Integer id);

}
