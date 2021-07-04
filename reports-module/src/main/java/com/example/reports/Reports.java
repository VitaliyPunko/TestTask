package com.example.reports;

import com.example.testtask.dao.dto.WorkerDaoDto;
import entity.dto.WorkerInfoAndHoursDto;
import entity.dto.WorkersAndDepartmentsEntityDto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Reports {

    public static void writeGeneralReport(WorkerDaoDto workerDaoDto) {
        try (FileWriter fileWriter = new FileWriter("General Report.txt", false)) {
            List<WorkersAndDepartmentsEntityDto> workersDtoList = workerDaoDto.findAllWorkersWithDepartmentName();
            for (WorkersAndDepartmentsEntityDto workers : workersDtoList) {
                String firstName = workers.getFirstName();
                String lastName = workers.getLastName();
                String email = workers.getEmail();
                String departmentName = workers.getDepartmentName();
                fileWriter.write(firstName + " " + lastName + " " + email + " " + departmentName + "\n");
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void writeIndividualReport(WorkerDaoDto workerDaoDto, int id) {
        try (FileWriter fileWriter = new FileWriter("Individual Report.txt", false)) {
            WorkerInfoAndHoursDto worker = workerDaoDto.findWorkerWithHisWorkedHours(id);
            fileWriter.write(worker.getFirstName() + " " + worker.getLastName() + " " + worker.getEmail() + " " + worker.getWorkerHours());
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
