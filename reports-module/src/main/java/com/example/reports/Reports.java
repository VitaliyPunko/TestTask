package com.example.reports;

import com.example.testtask.dao.dto.WorkerDaoDto;
import entity.dto.WorkerInfoAndHoursDto;
import entity.dto.WorkersAndDepartmentsEntityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Reports {

    private static final Logger LOGGER = LoggerFactory.getLogger(Reports.class);

    public static void writeGeneralReport(WorkerDaoDto workerDaoDto, boolean isConsole) {
        List<WorkersAndDepartmentsEntityDto> workersDtoList = workerDaoDto.findAllWorkersWithDepartmentName();
        if (isConsole) {
            LOGGER.debug("Output workers in console:");
            for (WorkersAndDepartmentsEntityDto workers : workersDtoList) {
                System.out.println(workers);
            }
            return;
        }
        try (FileWriter fileWriter = new FileWriter("General Report.txt", false)) {
            LOGGER.debug("Output workers in General Report.txt file:");
            for (WorkersAndDepartmentsEntityDto workers : workersDtoList) {
                int id = workers.getId();
                String firstName = workers.getFirstName();
                String lastName = workers.getLastName();
                String email = workers.getEmail();
                String departmentName = workers.getDepartmentName();
                fileWriter.write(id + " " + firstName + " " + lastName + " " + email + " " + departmentName + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeIndividualReport(WorkerDaoDto workerDaoDto, int id, boolean isConsole) {
        fileWriterIndividual(workerDaoDto, isConsole, id);
    }

    public static void writeIndividualReport(WorkerDaoDto workerDaoDto, boolean isConsole) {
        int id = 1;
        fileWriterIndividual(workerDaoDto, isConsole, id);
    }

    private static void fileWriterIndividual(WorkerDaoDto workerDaoDto, boolean isConsole, int id) {
        WorkerInfoAndHoursDto worker = workerDaoDto.findWorkerWithHisWorkedHours(id);
        if (isConsole) {
            LOGGER.debug("Output individualWorkers in console:");
            System.out.println(worker.toString());
            return;
        }
        try (FileWriter fileWriter = new FileWriter("Individual Report [" + id + "].txt", false)) {
            LOGGER.debug("Output individualWorkers in Individual Report [{}].txt file:", id);
            fileWriter.write(worker.getId() + " " + worker.getFirstName() + " " + worker.getLastName() + " "
                    + worker.getEmail() + " " + worker.getWorkerHours());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
