package com.example.testtask.dao.dto;

import entity.dto.WorkersEntityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class WorkerDaoDtoImpl implements WorkerDaoDto {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    RowMapper rowMapper = BeanPropertyRowMapper.newInstance(WorkersEntityDto.class);

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerDaoDtoImpl.class);

    @Value("${worker.findAllWorkersWithDepartmentName}")
    private String findAllWorkersWithDepartmentNameSQL;

    public WorkerDaoDtoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<WorkersEntityDto> findAllWorkersWithDepartmentName() {
        LOGGER.debug("find all workers with department name");
        return namedParameterJdbcTemplate.query(findAllWorkersWithDepartmentNameSQL, rowMapper);
    }
}
