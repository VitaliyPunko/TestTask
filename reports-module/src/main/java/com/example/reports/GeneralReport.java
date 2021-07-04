package com.example.reports;

import com.example.testtask.dao.dto.WorkerDaoDto;
import entity.dto.WorkersEntityDto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeneralReport {

    public static void writeGeneralReport(WorkerDaoDto workerDaoDto) {
        try (FileWriter fileWriter = new FileWriter("General Report.txt", false)) {
            List<WorkersEntityDto> workersDtoList = workerDaoDto.findAllWorkersWithDepartmentName();
            for (WorkersEntityDto workers : workersDtoList) {
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

//    public static void writeGeneralReport(CommonDao<DepartmentEntity> departmentDao) {
//        try (FileWriter fileWriter = new FileWriter("General Report.txt", false)) {
//            List<DepartmentEntity> departmentList = departmentDao.findAll();
//            for (DepartmentEntity departments : departmentList) {
//                String departmentId = Integer.toString(departments.getId());
//                String departmentName = departments.getDepartmentName();
//                fileWriter.write(departmentId + " " + departmentName + "\n");
//            }
//
//        } catch (IOException e) {
//            e.getMessage();
//        }
//    }
}
