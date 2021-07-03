package com.example.testtask.dao.department;

import entity.DepartmentEntity;
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
public class DepartmentDaoImpl implements DepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDaoImpl.class);

    @Value("${department.select}")
    private String selectSQL;

    @Value("${department.findById}")
    private String findByIdSQL;

    @Value("${department.create}")
    private String createSQL;

    @Value("${department.update}")
    private String updateSQL;

    @Value("${department.delete}")
    private String deleteSQL;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    RowMapper rowMapper = BeanPropertyRowMapper.newInstance(DepartmentEntity.class);

    @Autowired
    public DepartmentDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<DepartmentEntity> findAll() {
        LOGGER.debug("Find all departments");
        return namedParameterJdbcTemplate.query(selectSQL, rowMapper);
    }

    @Override
    public DepartmentEntity findById(Integer id) {
        LOGGER.debug("Find by id: {}", id);
        if (!isDepartmentIdCorrect(id)) {
            LOGGER.debug("Department with this id doesn't exist: {}", id);
            throw new IllegalArgumentException("Department with this id doesn't exist: " + id);
        }
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
        return (DepartmentEntity) namedParameterJdbcTemplate.queryForObject(findByIdSQL, sqlParameterSource, rowMapper);
    }

    @Override
    public Integer create(DepartmentEntity department) {
        LOGGER.debug("Create department: {}", department);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("department_name", department.getDepartmentName());
        namedParameterJdbcTemplate.update(createSQL, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public Integer update(DepartmentEntity department) {
        LOGGER.debug("Update department: {}", department);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("department_name", department.getDepartmentName())
                .addValue("id", department.getId());
        return namedParameterJdbcTemplate.update(updateSQL, sqlParameterSource);
    }

    @Override
    public Integer delete(Integer id) {
        LOGGER.debug("Delete department with id: {}", id);
        if (!isDepartmentIdCorrect(id)) {
            LOGGER.debug("Department with this id doesn't exist: {}", id);
            throw new IllegalArgumentException("Department with this id doesn't exist: " + id);
        }
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(deleteSQL, sqlParameterSource);
    }

    private boolean isDepartmentIdCorrect(Integer id) {
        List<DepartmentEntity> departmentList = findAll();
        List<Integer> idList = new ArrayList<>(departmentList.size());
        for (DepartmentEntity department : departmentList) {
            idList.add(department.getId());
        }
        return idList.contains(id);
    }
}
