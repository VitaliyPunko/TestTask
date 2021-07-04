package com.example.testtask.dao.dto;

import entity.dto.WorkersEntityDto;

import java.util.List;

public interface WorkerDaoDto {

    List<WorkersEntityDto> findAllWorkersWithDepartmentName();
}
