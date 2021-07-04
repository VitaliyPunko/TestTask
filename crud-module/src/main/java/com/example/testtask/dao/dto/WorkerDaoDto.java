package com.example.testtask.dao.dto;

import entity.dto.WorkerInfoAndHoursDto;
import entity.dto.WorkersAndDepartmentsEntityDto;

import java.util.List;

public interface WorkerDaoDto {

    List<WorkersAndDepartmentsEntityDto> findAllWorkersWithDepartmentName();

    WorkerInfoAndHoursDto findWorkerWithHisWorkedHours(Integer id);
}
