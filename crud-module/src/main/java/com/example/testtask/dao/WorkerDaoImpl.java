package com.example.testtask.dao;

import entity.DepartmentEntity;
import entity.WorkersEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WorkerDaoImpl implements CommonDao<WorkersEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerDaoImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    RowMapper rowMapper = BeanPropertyRowMapper.newInstance(WorkersEntity.class);
    RowMapper departmentRowMapper = BeanPropertyRowMapper.newInstance(DepartmentEntity.class);

    @Autowired
    public WorkerDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Value("${worker.select}")
    private String selectSQL;

    @Value("${worker.findById}")
    private String findByIdSQL;

    @Value("${worker.create}")
    private String createSQL;

    @Value("${worker.update}")
    private String updateSQL;

    @Value("${worker.delete}")
    private String deleteSQL;

    @Value("${worker.getDepartmentsId}")
    private String getDepartmentsIdSQL;

    @Override
    public List<WorkersEntity> findAll() {
        LOGGER.debug("Find all workers");
        return namedParameterJdbcTemplate.query(selectSQL, rowMapper);
    }

    @Override
    public WorkersEntity findById(Integer id) {
        LOGGER.debug("Find worker by id: {}", id);
        if (!isWorkerIdCorrect(id)) {
            LOGGER.debug("Worker with this id doesn't exist: {}", id);
            throw new IllegalArgumentException("Worker with this id doesn't exist: " + id);
        }
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
        return (WorkersEntity) namedParameterJdbcTemplate.queryForObject(findByIdSQL, sqlParameterSource, rowMapper);
    }

    @Override
    public Integer create(WorkersEntity worker) {
        LOGGER.debug("Create worker: {}", worker);
        if (!isDepartmentIdCorrect(worker)) {
            LOGGER.debug("Department with this id doesn't exist: {}", worker.getDepartmentId());
            throw new IllegalArgumentException("Department with this id doesn't exist : "
                    + worker.getDepartmentId() + ". Please, enter the correct id");
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("First_name", worker.getFirstName())
                .addValue("Last_name", worker.getLastName())
                .addValue("Email", worker.getEmail())
                .addValue("Department_id", worker.getDepartmentId());
        namedParameterJdbcTemplate.update(createSQL, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public Integer update(WorkersEntity worker) {
        LOGGER.debug("Update worker: {}", worker);
        if (!isDepartmentIdCorrect(worker)) {
            LOGGER.debug("Department with this id doesn't exist: {}", worker.getDepartmentId());
            throw new IllegalArgumentException("Department with this id doesn't exist : "
                    + worker.getDepartmentId() + ". Please, enter the correct id");
        }
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("First_name", worker.getFirstName())
                .addValue("Last_name", worker.getLastName())
                .addValue("Email", worker.getEmail())
                .addValue("Department_id", worker.getDepartmentId())
                .addValue("id", worker.getId());
        return namedParameterJdbcTemplate.update(updateSQL, sqlParameterSource);
    }

    @Override
    public Integer delete(Integer id) {
        LOGGER.debug("Delete worker with id: {}", id);
        if (!isWorkerIdCorrect(id)) {
            LOGGER.debug("Worker with this id doesn't exist: {}", id);
            throw new IllegalArgumentException("Worker with this id doesn't exist: " + id);
        }
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(deleteSQL, sqlParameterSource);
    }

    private boolean isWorkerIdCorrect(Integer id) {
        List<WorkersEntity> workersList = findAll();
        List<Integer> idList = new ArrayList<>(workersList.size());
        for (WorkersEntity workers : workersList) {
            idList.add(workers.getId());
        }
        return idList.contains(id);
    }

    private boolean isDepartmentIdCorrect(WorkersEntity worker) {
        List<DepartmentEntity> departmentsList = namedParameterJdbcTemplate.query(getDepartmentsIdSQL, departmentRowMapper);
        List<Integer> integerList = new ArrayList<>(departmentsList.size());
        for (DepartmentEntity departments : departmentsList) {
            integerList.add(departments.getId());
        }
        if (!integerList.contains(worker.getDepartmentId())) {
            LOGGER.debug("isDepartmentIdCorrect method. Department with this is doesn't exist: {}", worker.getDepartmentId());
            return false;
        }
        return true;
    }
}
