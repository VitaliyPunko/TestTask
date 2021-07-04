package com.example.testtask.dao.dto;

import entity.dto.WorkerInfoAndHoursDto;
import entity.dto.WorkersAndDepartmentsEntityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class WorkerDaoDtoImpl implements WorkerDaoDto {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    RowMapper rowMapperWithDepartment = BeanPropertyRowMapper.newInstance(WorkersAndDepartmentsEntityDto.class);
    RowMapper rowMapperWithHours = BeanPropertyRowMapper.newInstance(WorkerInfoAndHoursDto.class);

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerDaoDtoImpl.class);

    @Value("${worker.findAllWorkersWithDepartmentName}")
    private String findAllWorkersWithDepartmentNameSQL;

    @Value("${worker.findWorkerWithHisWorkedHours}")
    private String findWorkerWithHisWorkedHoursSQL;

    public WorkerDaoDtoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<WorkersAndDepartmentsEntityDto> findAllWorkersWithDepartmentName() {
        LOGGER.debug("find all workers with department name");
        return namedParameterJdbcTemplate.query(findAllWorkersWithDepartmentNameSQL, rowMapperWithDepartment);
    }

    @Override
    public WorkerInfoAndHoursDto findWorkerWithHisWorkedHours(Integer id) {
        LOGGER.debug("find worker with his worked hours");
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
        return (WorkerInfoAndHoursDto) namedParameterJdbcTemplate.queryForObject(findWorkerWithHisWorkedHoursSQL, sqlParameterSource, rowMapperWithHours);
    }
}
