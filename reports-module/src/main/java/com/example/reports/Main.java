package com.example.reports;

import com.example.reports.config.ReportsConfiguration;
import com.example.testtask.dao.dto.WorkerDaoDto;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ReportsConfiguration.class);
        WorkerDaoDto workerDaoDto = context.getBean(WorkerDaoDto.class);

        boolean isConsole = false;
        if (args.length > 0) {
            isConsole = args[0].equals("-c") || args[0].equals("-console");
        }
        if (args[1] != null) {
            int id = Integer.parseInt(args[1]);
            Reports.writeIndividualReport(workerDaoDto, id, isConsole);
        } else {
            Reports.writeIndividualReport(workerDaoDto, isConsole);
        }

        Reports.writeGeneralReport(workerDaoDto, isConsole);


    }
}
