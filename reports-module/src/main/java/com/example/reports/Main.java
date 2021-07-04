package com.example.reports;

import com.example.reports.config.ReportsConfiguration;
import com.example.testtask.dao.dto.WorkerDaoDto;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ReportsConfiguration.class);

        WorkerDaoDto workerDaoDto = context.getBean(WorkerDaoDto.class);
        GeneralReport.writeGeneralReport(workerDaoDto);

    }
}
