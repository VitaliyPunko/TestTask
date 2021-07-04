package com.example.testtask.service.worker;

import com.example.testtask.dao.CommonDao;
import com.example.testtask.service.CommonService;
import entity.WorkersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkerServiceImpl implements CommonService<WorkersEntity> {

    CommonDao<WorkersEntity> workerDao;

    @Autowired
    public WorkerServiceImpl(CommonDao<WorkersEntity> workerDao) {
        this.workerDao = workerDao;
    }

    @Override
    public List<WorkersEntity> findAll() {
        return workerDao.findAll();
    }

    @Override
    public WorkersEntity findById(Integer id) {
        return null;
    }

    @Override
    public Integer create(WorkersEntity entity) {
        return null;
    }

    @Override
    public Integer update(WorkersEntity entity) {
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return null;
    }
}
