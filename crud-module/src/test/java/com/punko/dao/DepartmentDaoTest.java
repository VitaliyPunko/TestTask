package com.punko.dao;

import com.example.testtask.TestTaskApplication;
import com.example.testtask.dao.CommonDao;
import com.example.testtask.dao.DepartmentDaoImpl;
import entity.DepartmentEntity;
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
@Import({DepartmentDaoImpl.class})
@PropertySource({"classpath:department.properties"})
@ContextConfiguration(classes = TestTaskApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DepartmentDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDaoTest.class);

    @Autowired
    private CommonDao<DepartmentEntity> departmentDao;

//    @Autowired
//    private DepartmentDao departmentDao;

    @Test
    public void findAllTest() {
        LOGGER.debug("should find all departments()");
        List<DepartmentEntity> departmentList = departmentDao.findAll();
        Assertions.assertNotNull(departmentList);
        Assertions.assertTrue(departmentList.size() > 0);
    }

    @Test
    public void findByIdTest() {
        LOGGER.debug("should find department by id()");
        List<DepartmentEntity> departmentList = departmentDao.findAll();
        Assertions.assertNotNull(departmentList);
        Assertions.assertTrue(departmentList.size() > 0);

        Integer departmentId = departmentList.get(0).getId();
        DepartmentEntity department = departmentDao.findById(departmentId);
        Assertions.assertNotNull(department);
        Assertions.assertEquals(department.getDepartmentName(), departmentList.get(0).getDepartmentName());
    }

    @Test
    public void findByIdExceptionTest() {
        LOGGER.debug("should find exception by id()");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            departmentDao.findById(999);
        });
    }

    @Test
    public void createTest() {
        LOGGER.debug("should create department()");
        List<DepartmentEntity> departmentList = departmentDao.findAll();
        Integer countBeforeCreate = departmentList.size();
        departmentDao.create(new DepartmentEntity("newSomeDep"));
        List<DepartmentEntity> departmentListAfterCreate = departmentDao.findAll();
        Integer countAfterCreate = departmentListAfterCreate.size();
        Assertions.assertEquals(countBeforeCreate + 1, countAfterCreate);
    }

    @Test
    public void updateApartmentTest() {
        LOGGER.debug("should update department()");
        List<DepartmentEntity> departmentList = departmentDao.findAll();
        Assertions.assertNotNull(departmentList);
        Assertions.assertTrue(departmentList.size() > 0);

        DepartmentEntity department = departmentList.get(0);
        department.setDepartmentName("anotherDep");

        departmentDao.update(department);
        DepartmentEntity realDepartment = departmentDao.findById(department.getId());
        Assertions.assertEquals("anotherDep", realDepartment.getDepartmentName());
        Assertions.assertEquals(realDepartment, department);
    }

    @Test
    public void deleteDepartmentTest() {
        LOGGER.debug("should delete department()");
        List<DepartmentEntity> departmentList = departmentDao.findAll();
        Assertions.assertNotNull(departmentList);
        Assertions.assertTrue(departmentList.size() > 0);

        Integer countBeforeDelete = departmentList.size();
        departmentDao.delete(departmentList.get(0).getId());
        List<DepartmentEntity> departmentListAfterCreate = departmentDao.findAll();
        Integer countAfterDelete = departmentListAfterCreate.size();
        Assertions.assertEquals(countBeforeDelete, countAfterDelete + 1);
    }
}
