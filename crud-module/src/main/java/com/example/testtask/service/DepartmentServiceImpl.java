package com.example.testtask.service;

import com.example.testtask.dao.CommonDao;
import entity.DepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements CommonService<DepartmentEntity> {

    private final CommonDao<DepartmentEntity> departmentDao;

    @Autowired
    public DepartmentServiceImpl(CommonDao<DepartmentEntity> departmentDao) {
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
        return department.getId() == null ? departmentDao.create(department) : update(department);
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
