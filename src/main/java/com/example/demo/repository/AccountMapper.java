package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.AccountEntity;

@Mapper
public interface AccountMapper {

	AccountEntity findByAccountName(String accountName);
    
}