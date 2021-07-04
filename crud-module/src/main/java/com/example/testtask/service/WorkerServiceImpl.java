package com.example.testtask.service;

import com.example.testtask.dao.CommonDao;
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
        return workerDao.findById(id);
    }

    @Override
    public Integer create(WorkersEntity worker) {
        return worker.getId() == null ? workerDao.create(worker) : update(worker);
    }

    @Override
    public Integer update(WorkersEntity worker) {
        return workerDao.update(worker);
    }

    @Override
    public Integer delete(Integer id) {
        return workerDao.delete(id);
    }
}
