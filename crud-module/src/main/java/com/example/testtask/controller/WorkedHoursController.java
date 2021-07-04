package com.example.testtask.controller;

import com.example.testtask.service.CommonService;
import entity.WorkedHoursEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkedHoursController {

    @Autowired
    CommonService<WorkedHoursEntity> workedHoursService;

    @GetMapping("/worked_hours")
    public List<WorkedHoursEntity> findAllWorkedHours() {
        return workedHoursService.findAll();
    }


}
