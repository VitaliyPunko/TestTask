package com.example.reports;

import com.example.testtask.dao.CommonDao;
import entity.DepartmentEntity;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeneralReport {

    public static void writeGeneralReport(CommonDao<DepartmentEntity> departmentDao) {
        try (FileWriter fileWriter = new FileWriter("General Report.txt", false)) {
            List<DepartmentEntity> departmentList = departmentDao.findAll();
            for (DepartmentEntity departments : departmentList) {
                String departmentId = Integer.toString(departments.getId());
                String departmentName = departments.getDepartmentName();
                fileWriter.write(departmentId + " " + departmentName + "\n");
            }

        } catch (IOException e) {
            e.getMessage();
        }
    }
}
