package com.example.testtask.service.department;

import entity.DepartmentEntity;

import java.util.List;

public interface DepartmentService {

    List<DepartmentEntity> findAll();

    DepartmentEntity findById(Integer id);

    Integer create(DepartmentEntity department);

    Integer update(DepartmentEntity department);

    Integer delete(Integer id);
}
