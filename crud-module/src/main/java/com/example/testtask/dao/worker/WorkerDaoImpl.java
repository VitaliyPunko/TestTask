package com.example.testtask.dao.worker;

import com.example.testtask.dao.CommonDao;
import entity.WorkersEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class WorkerDaoImpl implements CommonDao<WorkersEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerDaoImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    RowMapper rowMapper = BeanPropertyRowMapper.newInstance(WorkersEntity.class);

    @Autowired
    public WorkerDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Value("${worker.select}")
    private String selectSQL;

    @Override
    public List<WorkersEntity> findAll() {
        LOGGER.debug("Find all workers");
        return namedParameterJdbcTemplate.query(selectSQL, rowMapper);
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
