package com.example.testtask.controller;

import com.example.testtask.service.CommonService;
import entity.DepartmentEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    CommonService<DepartmentEntity> departmentService;

    @GetMapping("/departments")
    public List<DepartmentEntity> findAllDepartments() {
        LOGGER.debug("find all departments from controller");
        return departmentService.findAll();
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<DepartmentEntity> findDepartmentById(@PathVariable Integer id) {
        LOGGER.debug("find department by id from controller");
        DepartmentEntity department = departmentService.findById(id);
        return department != null ? new ResponseEntity<>(department, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/departments")
    public ResponseEntity<Integer> createDepartment(@RequestBody DepartmentEntity department) {
        LOGGER.debug("create or update department from controller");
        Integer id = departmentService.create(department);
        return id > 0 ? new ResponseEntity<>(id, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Integer> deleteDepartment(@PathVariable Integer id) {
        LOGGER.debug("delete department by id from controller");
        Integer result = departmentService.delete(id);
        return result > 0 ? new ResponseEntity<>(result, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
