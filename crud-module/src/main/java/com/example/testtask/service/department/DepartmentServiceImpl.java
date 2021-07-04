package com.example.testtask.service.department;

import com.example.testtask.dao.DepartmentDao;
import entity.DepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public List<DepartmentEntity> findAll() {
        return departmentDao.findAll();
    }

    @Override
    public DepartmentEntity findById(Integer id) {
        return departmentDao.findById(id);
    }

    @Override
    public Integer create(DepartmentEntity department) {
        if (department.getId() > 0) {
            return update(department);
        }
        return departmentDao.create(department);
    }

    @Override
    public Integer update(DepartmentEntity department) {
        return departmentDao.update(department);
    }

    @Override
    public Integer delete(Integer id) {
        return departmentDao.delete(id);
    }
}
