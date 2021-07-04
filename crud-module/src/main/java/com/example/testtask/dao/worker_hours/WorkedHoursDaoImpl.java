package com.example.testtask.dao.worker_hours;

import com.example.testtask.dao.CommonDao;
import entity.WorkedHoursEntity;
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
public class WorkedHoursDaoImpl implements CommonDao<WorkedHoursEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkedHoursDaoImpl.class);

    @Value("${workedHours.select}")
    private String selectSQL;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    RowMapper rowMapper = BeanPropertyRowMapper.newInstance(WorkedHoursEntity.class);

    @Autowired
    public WorkedHoursDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<WorkedHoursEntity> findAll() {
        return namedParameterJdbcTemplate.query(selectSQL, rowMapper);
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
