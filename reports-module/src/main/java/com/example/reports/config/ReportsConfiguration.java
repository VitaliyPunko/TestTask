package com.example.reports.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.example")
public class ReportsConfiguration {

    @Autowired
    DataSource dataSource;
}
