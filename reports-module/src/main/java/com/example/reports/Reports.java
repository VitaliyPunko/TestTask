package com.example.reports;

import com.example.testtask.dao.dto.WorkerDaoDto;
import entity.dto.WorkerInfoAndHoursDto;
import entity.dto.WorkersAndDepartmentsEntityDto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Reports {

    public static void writeGeneralReport(WorkerDaoDto workerDaoDto, boolean isConsole) {
        try (FileWriter fileWriter = new FileWriter("General Report.txt", false)) {
            List<WorkersAndDepartmentsEntityDto> workersDtoList = workerDaoDto.findAllWorkersWithDepartmentName();
            if (isConsole) {
                for (WorkersAndDepartmentsEntityDto workers : workersDtoList) {
                    System.out.println(workers);
                }
                return;
            }
            for (WorkersAndDepartmentsEntityDto workers : workersDtoList) {
                int id = workers.getId();
                String firstName = workers.getFirstName();
                String lastName = workers.getLastName();
                String email = workers.getEmail();
                String departmentName = workers.getDepartmentName();
                fileWriter.write(id + " " + firstName + " " + lastName + " " + email + " " + departmentName + "\n");
            }
        } catch (IOException e) {
            e.getMessage();
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
        try (FileWriter fileWriter = new FileWriter("Individual Report [" + id + "].txt", false)) {
            WorkerInfoAndHoursDto worker = workerDaoDto.findWorkerWithHisWorkedHours(id);
            if (isConsole) {
                System.out.println(worker.toString());
                return;
            }
            fileWriter.write(worker.getId() + " " + worker.getFirstName() + " " + worker.getLastName() + " "
                    + worker.getEmail() + " " + worker.getWorkerHours());
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
