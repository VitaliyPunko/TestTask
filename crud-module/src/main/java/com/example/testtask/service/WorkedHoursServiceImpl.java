package com.example.testtask.service;

import com.example.testtask.dao.CommonDao;
import entity.WorkedHoursEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkedHoursServiceImpl implements CommonService<WorkedHoursEntity> {

    CommonDao<WorkedHoursEntity> workedHoursDao;

    @Autowired
    public WorkedHoursServiceImpl(CommonDao<WorkedHoursEntity> workedHoursDao) {
        this.workedHoursDao = workedHoursDao;
    }

    @Override
    public List<WorkedHoursEntity> findAll() {
        return workedHoursDao.findAll();
    }

    @Override
    public WorkedHoursEntity findById(Integer id) {
        return null;
    }

    @Override
    public Integer create(WorkedHoursEntity entity) {
        return null;
    }

    @Override
    public Integer update(WorkedHoursEntity entity) {
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return null;
    }
}
