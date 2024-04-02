package com.example.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.demo.repository")
public class MyBatisConfig {
    // Cấu hình các bean khác của MyBatis (SqlSessionFactory, SqlSessionTemplate, ...)
}
