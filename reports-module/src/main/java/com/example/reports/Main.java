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
            if (args.length == 1) {
                if (args[0].equals("-c") || args[0].equals("-console")) {
                    isConsole = true;
                    Reports.writeIndividualReport(workerDaoDto, true);
                } else {
                    int id = Integer.parseInt(args[0]);
                    Reports.writeIndividualReport(workerDaoDto, id, false);
                }
            }
            if (args.length == 2) {
                int id = Integer.parseInt(args[1]);
                isConsole = true;
                Reports.writeIndividualReport(workerDaoDto, id, true);
            }
        }
        Reports.writeGeneralReport(workerDaoDto, isConsole);

    }
}
