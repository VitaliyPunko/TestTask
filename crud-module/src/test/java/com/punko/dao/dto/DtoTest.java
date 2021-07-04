package com.punko.dao.dto;

import com.example.testtask.TestTaskApplication;
import com.example.testtask.dao.dto.WorkerDaoDto;
import com.example.testtask.dao.dto.WorkerDaoDtoImpl;
import entity.dto.WorkerInfoAndHoursDto;
import entity.dto.WorkersAndDepartmentsEntityDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@DataJdbcTest
@Import({WorkerDaoDtoImpl.class})
@PropertySource({"classpath:worker.properties"})
@ContextConfiguration(classes = TestTaskApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DtoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DtoTest.class);

    @Autowired
    private WorkerDaoDto workerDaoDto;

    @Test
    public void findAllWorkersWithDepartmentNameTest() {
        LOGGER.debug("should find all workersWithDepartmentName()");
        List<WorkersAndDepartmentsEntityDto> workersList = workerDaoDto.findAllWorkersWithDepartmentName();
        Assertions.assertNotNull(workersList);
        Assertions.assertTrue(workersList.size() > 0);
    }

    @Test
    public void findWorkerWithHisWorkedHoursTest() {
        LOGGER.debug("should findWorkerWithHisWorkedHours by id()");
        WorkerInfoAndHoursDto worker = workerDaoDto.findWorkerWithHisWorkedHours(1);
        Assertions.assertNotNull(worker);
    }
}
