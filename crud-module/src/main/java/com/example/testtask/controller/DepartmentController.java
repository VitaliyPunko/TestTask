package com.example.testtask.controller;

import com.example.testtask.service.department.DepartmentService;
import entity.DepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/departments")
    public List<DepartmentEntity> findAllDepartments() {
        return departmentService.findAll();
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<DepartmentEntity> findDepartmentById(@PathVariable Integer id) {
        DepartmentEntity department = departmentService.findById(id);
        return department != null ? new ResponseEntity<>(department, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/departments")
    public ResponseEntity<Integer> createDepartment(@RequestBody DepartmentEntity department) {
        Integer id = departmentService.create(department);
        return id > 0 ? new ResponseEntity<>(id, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Integer> deleteDepartment(@PathVariable Integer id) {
        Integer result = departmentService.delete(id);
        return result > 0 ? new ResponseEntity<>(result, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
