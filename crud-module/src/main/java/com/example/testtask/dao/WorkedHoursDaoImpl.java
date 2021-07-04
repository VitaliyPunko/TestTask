package com.example.testtask.dao;

import entity.WorkedHoursEntity;
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
public class WorkedHoursDaoImpl implements CommonDao<WorkedHoursEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkedHoursDaoImpl.class);

    @Value("${workedHours.select}")
    private String selectSQL;

    @Value("${workedHours.findById}")
    private String findByIdSQL;

    @Value("${workedHours.create}")
    private String createSQL;

    @Value("${workedHours.update}")
    private String updateSQL;

    @Value("${workedHours.delete}")
    private String deleteSQL;

    @Value("${workedHours.getWorkersId}")
    private String getWorkersIdSQL;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    RowMapper rowMapper = BeanPropertyRowMapper.newInstance(WorkedHoursEntity.class);
    RowMapper workerRowMapper = BeanPropertyRowMapper.newInstance(WorkersEntity.class);

    @Autowired
    public WorkedHoursDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<WorkedHoursEntity> findAll() {
        LOGGER.debug("Find all workedHours");
        return namedParameterJdbcTemplate.query(selectSQL, rowMapper);
    }

    @Override
    public WorkedHoursEntity findById(Integer id) {
        LOGGER.debug("Find workedHours by id: {}", id);
        if (!isWorkedHoursIdCorrect(id)) {
            LOGGER.debug("WorkedHours with this id doesn't exist: {}", id);
            throw new IllegalArgumentException("WorkedHours with this id doesn't exist: " + id);
        }
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
        return (WorkedHoursEntity) namedParameterJdbcTemplate.queryForObject(findByIdSQL, sqlParameterSource, rowMapper);
    }

    @Override
    public Integer create(WorkedHoursEntity workedHours) {
        LOGGER.debug("Create workedHours: {}", workedHours);
        if (!isWorkerIdCorrect(workedHours)) {
            LOGGER.debug("Worker with this id doesn't exist: {}", workedHours.getWorkerId());
            throw new IllegalArgumentException("Worker with this id doesn't exist : "
                    + workedHours.getWorkerId() + ". Please, enter the correct id");
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("Worker_id", workedHours.getWorkerId())
                .addValue("Worker_hours", workedHours.getWorkerHours());
        namedParameterJdbcTemplate.update(createSQL, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public Integer update(WorkedHoursEntity workedHours) {
        LOGGER.debug("Update workedHours: {}", workedHours);
        if (!isWorkerIdCorrect(workedHours)) {
            LOGGER.debug("Worker with this id doesn't exist: {}", workedHours.getWorkerId());
            throw new IllegalArgumentException("Worker with this id doesn't exist : "
                    + workedHours.getWorkerId() + ". Please, enter the correct id");
        }
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("Worker_id", workedHours.getWorkerId())
                .addValue("Worker_hours", workedHours.getWorkerHours())
                .addValue("id", workedHours.getId());
        return namedParameterJdbcTemplate.update(updateSQL, sqlParameterSource);
    }

    @Override
    public Integer delete(Integer id) {
        LOGGER.debug("Delete workedHours with id: {}", id);
        if (!isWorkedHoursIdCorrect(id)) {
            LOGGER.debug("WorkedHours with this id doesn't exist: {}", id);
            throw new IllegalArgumentException("WorkedHours with this id doesn't exist: " + id);
        }
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(deleteSQL, sqlParameterSource);
    }

    private boolean isWorkedHoursIdCorrect(Integer id) {
        List<WorkedHoursEntity> workedHoursList = findAll();
        List<Integer> idList = new ArrayList<>(workedHoursList.size());
        for (WorkedHoursEntity hours : workedHoursList) {
            idList.add(hours.getId());
        }
        return idList.contains(id);
    }

    private boolean isWorkerIdCorrect(WorkedHoursEntity workedHours) {
        List<WorkersEntity> workerList = namedParameterJdbcTemplate.query(getWorkersIdSQL, workerRowMapper);
        List<Integer> integerList = new ArrayList<>(workerList.size());
        for (WorkersEntity workers : workerList) {
            integerList.add(workers.getId());
        }
        if (!integerList.contains(workedHours.getWorkerId())) {
            LOGGER.debug("isWorkerIdCorrect method. Worker with this is doesn't exist: {}", workedHours.getWorkerId());
            return false;
        }
        return true;
    }
}
