package com.example.testtask.service.worked_hours;

import com.example.testtask.dao.CommonDao;
import com.example.testtask.service.CommonService;
import entity.WorkedHoursEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkedHoursServiceImpl implements CommonService<WorkedHoursEntity> {

    CommonDao<WorkedHoursEntity> commonDao;

    @Autowired
    public WorkedHoursServiceImpl(CommonDao<WorkedHoursEntity> commonDao) {
        this.commonDao = commonDao;
    }

    @Override
    public List<WorkedHoursEntity> findAll() {
        return commonDao.findAll();
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
