package com.example.testtask.controller;

import com.example.testtask.service.CommonService;
import entity.WorkedHoursEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkedHoursController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkedHoursController.class);

    @Autowired
    CommonService<WorkedHoursEntity> workedHoursService;

    @GetMapping("/worked_hours")
    public List<WorkedHoursEntity> findAllWorkedHours() {
        LOGGER.debug("find all workedHours from controller");
        return workedHoursService.findAll();
    }

    @GetMapping("/worked_hours/{workerId}")
    public ResponseEntity<WorkedHoursEntity> findWorkedHoursById(@PathVariable Integer workerId) {
        LOGGER.debug("find workedHour by workerId from controller");
        WorkedHoursEntity workedHour = workedHoursService.findById(workerId);
        return workedHour != null ? new ResponseEntity<>(workedHour, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/worked_hours")
    public ResponseEntity<Integer> createWorkedHours(@RequestBody WorkedHoursEntity workedHoursEntity) {
        LOGGER.debug("create or update workedHoursEntity from controller");
        Integer id = workedHoursService.create(workedHoursEntity);
        return id > 0 ? new ResponseEntity<>(id, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/worked_hours/{workerId}")
    public ResponseEntity<Integer> deleteWorker(@PathVariable Integer workerId) {
        LOGGER.debug("delete workedHoursEntity by id from controller");
        Integer result = workedHoursService.delete(workerId);
        return result > 0 ? new ResponseEntity<>(result, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
