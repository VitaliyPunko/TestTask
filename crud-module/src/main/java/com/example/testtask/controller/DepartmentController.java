package com.example.testtask.controller;

import com.example.testtask.dao.department.DepartmentDao;
import entity.DepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/departments")
    public List<DepartmentEntity> findAllDepartments() {
        return departmentDao.findAll();
    }
}
