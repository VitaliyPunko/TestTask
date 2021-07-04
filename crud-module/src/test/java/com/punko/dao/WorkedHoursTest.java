package com.punko.dao;

import com.example.testtask.TestTaskApplication;
import com.example.testtask.dao.CommonDao;
import com.example.testtask.dao.WorkedHoursDaoImpl;
import entity.WorkedHoursEntity;
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
@Import({WorkedHoursDaoImpl.class})
@PropertySource({"classpath:workedHours.properties"})
@ContextConfiguration(classes = TestTaskApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WorkedHoursTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkedHoursTest.class);

    @Autowired
    private CommonDao<WorkedHoursEntity> workedHoursDao;

    @Test
    public void findAllTest() {
        LOGGER.debug("should find all workedHours()");
        List<WorkedHoursEntity> workedHoursList = workedHoursDao.findAll();
        Assertions.assertNotNull(workedHoursList);
        Assertions.assertTrue(workedHoursList.size() > 0);
    }

    @Test
    public void findByIdTest() {
        LOGGER.debug("should find workedHour by id()");
        List<WorkedHoursEntity> workedHoursList = workedHoursDao.findAll();
        Assertions.assertNotNull(workedHoursList);
        Assertions.assertTrue(workedHoursList.size() > 0);

        Integer workedHourId = workedHoursList.get(0).getId();
        WorkedHoursEntity workedHoursEntity = workedHoursDao.findById(workedHourId);
        Assertions.assertNotNull(workedHoursEntity);
        Assertions.assertEquals(workedHoursEntity.getWorkerHours(), workedHoursList.get(0).getWorkerHours());
    }

    @Test
    public void findByIdExceptionTest() {
        LOGGER.debug("should find exception by id()");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            workedHoursDao.findById(999999);
        });
    }

    @Test
    public void createTest() {
        LOGGER.debug("should create workedHour()");
        List<WorkedHoursEntity> workedHoursList = workedHoursDao.findAll();
        workedHoursDao.create(new WorkedHoursEntity(20, 1));

        List<WorkedHoursEntity> listAfterCreate = workedHoursDao.findAll();
        Assertions.assertEquals(workedHoursList.size() + 1, listAfterCreate.size());
    }

    @Test
    public void updateTest() {
        LOGGER.debug("should update workedHour()");
        List<WorkedHoursEntity> workedHoursEntities = workedHoursDao.findAll();
        Assertions.assertNotNull(workedHoursEntities);
        Assertions.assertTrue(workedHoursEntities.size() > 0);

        WorkedHoursEntity workedHoursEntity = workedHoursEntities.get(0);
        workedHoursEntity.setWorkerHours(350);

        workedHoursDao.update(workedHoursEntity);
        WorkedHoursEntity realWorkedHour = workedHoursDao.findById(workedHoursEntity.getId());
        Assertions.assertEquals(350, realWorkedHour.getWorkerHours());
        Assertions.assertEquals(realWorkedHour, workedHoursEntity);
    }

    @Test
    public void deleteWorkedHourTest() {
        LOGGER.debug("should delete workedHour()");
        List<WorkedHoursEntity> workedHoursEntities = workedHoursDao.findAll();
        Assertions.assertNotNull(workedHoursEntities);
        Assertions.assertTrue(workedHoursEntities.size() > 0);

        workedHoursDao.delete(workedHoursEntities.get(0).getId());
        List<WorkedHoursEntity> listAfterCreate = workedHoursDao.findAll();
        Assertions.assertEquals(workedHoursEntities.size(), listAfterCreate.size() + 1);
    }

}
