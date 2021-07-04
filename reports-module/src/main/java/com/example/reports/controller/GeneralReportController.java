package com.example.reports.controller;

import com.example.testtask.dao.CommonDao;
import entity.DepartmentEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralReportController {

//    @Autowired
//    CommonService<DepartmentEntity> departmentService;

    //   @Autowired
    CommonDao<DepartmentEntity> departmentDao;

}
