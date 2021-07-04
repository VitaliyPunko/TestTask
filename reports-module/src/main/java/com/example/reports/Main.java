package com.example.reports;

import com.example.reports.config.ReportsConfiguration;
import com.example.testtask.dao.CommonDao;
import entity.DepartmentEntity;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ReportsConfiguration.class);

        CommonDao<DepartmentEntity> departmentDao = context.getBean("departmentDaoImpl", CommonDao.class);

        GeneralReport.writeGeneralReport(departmentDao);

    }
}
