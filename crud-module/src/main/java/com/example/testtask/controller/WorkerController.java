package com.example.testtask.controller;

import com.example.testtask.service.CommonService;
import entity.WorkersEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerController.class);

    @Autowired
    CommonService<WorkersEntity> workersService;

    @GetMapping("/workers")
    public List<WorkersEntity> findAllWorkers() {
        LOGGER.debug("find all workers from controller");
        return workersService.findAll();
    }

    @GetMapping("/workers/{id}")
    public ResponseEntity<WorkersEntity> findWorkerById(@PathVariable Integer id) {
        LOGGER.debug("find worker by id from controller");
        WorkersEntity worker = workersService.findById(id);
        return worker != null ? new ResponseEntity<>(worker, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/workers")
    public ResponseEntity<Integer> createWorker(@RequestBody WorkersEntity worker) {
        LOGGER.debug("create or update worker from controller");
        Integer id = workersService.create(worker);
        return id > 0 ? new ResponseEntity<>(id, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/workers/{id}")
    public ResponseEntity<Integer> deleteWorker(@PathVariable Integer id) {
        LOGGER.debug("delete worker by id from controller");
        Integer result = workersService.delete(id);
        return result > 0 ? new ResponseEntity<>(result, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
