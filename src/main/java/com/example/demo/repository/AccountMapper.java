package com.example.demo.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

	Map<String, Object> findByAccountName(String accountName);
    
}