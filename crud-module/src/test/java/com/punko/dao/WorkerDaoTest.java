package com.punko.dao;

import com.example.testtask.TestTaskApplication;
import com.example.testtask.dao.CommonDao;
import com.example.testtask.dao.WorkerDaoImpl;
import entity.WorkersEntity;
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
@Import({WorkerDaoImpl.class})
@PropertySource({"classpath:worker.properties"})
@ContextConfiguration(classes = TestTaskApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WorkerDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerDaoTest.class);

    @Autowired
    private CommonDao<WorkersEntity> workerDao;

    @Test
    public void findAllTest() {
        LOGGER.debug("should find all workers()");
        List<WorkersEntity> workerList = workerDao.findAll();
        Assertions.assertNotNull(workerList);
        Assertions.assertTrue(workerList.size() > 0);
    }

    @Test
    public void findByIdTest() {
        LOGGER.debug("should find worker by id()");
        List<WorkersEntity> workerList = workerDao.findAll();
        Assertions.assertNotNull(workerList);
        Assertions.assertTrue(workerList.size() > 0);

        Integer workerId = workerList.get(0).getId();
        WorkersEntity worker = workerDao.findById(workerId);
        Assertions.assertNotNull(worker);
        Assertions.assertEquals(worker.getFirstName(), workerList.get(0).getFirstName());
    }

    @Test
    public void findByIdExceptionTest() {
        LOGGER.debug("should find exception by id()");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            workerDao.findById(999999);
        });
    }

    @Test
    public void createTest() {
        LOGGER.debug("should create worker()");
        List<WorkersEntity> workerList = workerDao.findAll();
        workerDao.create(new WorkersEntity("Firstname", "Lastname", "email", 1));

        List<WorkersEntity> listAfterCreate = workerDao.findAll();
        Assertions.assertEquals(workerList.size() + 1, listAfterCreate.size());
    }

    @Test
    public void updateWorkerTest() {
        LOGGER.debug("should update worker()");
        List<WorkersEntity> workerList = workerDao.findAll();
        Assertions.assertNotNull(workerList);
        Assertions.assertTrue(workerList.size() > 0);

        WorkersEntity worker = workerList.get(0);
        worker.setFirstName("someName");

        workerDao.update(worker);
        WorkersEntity realWorker = workerDao.findById(worker.getId());
        Assertions.assertEquals("someName", realWorker.getFirstName());
        Assertions.assertEquals(realWorker, worker);
    }

    @Test
    public void deleteWorkerTest() {
        LOGGER.debug("should delete worker()");
        List<WorkersEntity> workerList = workerDao.findAll();
        Assertions.assertNotNull(workerList);
        Assertions.assertTrue(workerList.size() > 0);

        workerDao.delete(workerList.get(0).getId());
        List<WorkersEntity> listAfterCreate = workerDao.findAll();
        Assertions.assertEquals(workerList.size(), listAfterCreate.size() + 1);
    }

}
