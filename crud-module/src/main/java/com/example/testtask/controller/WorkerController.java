package com.example.testtask.controller;

import com.example.testtask.service.CommonService;
import entity.WorkersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkerController {

    @Autowired
    CommonService<WorkersEntity> workersService;

    @GetMapping("/workers")
    public List<WorkersEntity> findAllDepartments() {
        return workersService.findAll();
    }


}
