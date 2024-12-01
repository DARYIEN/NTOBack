package com.example.nto.service.impl;

import com.example.nto.service.EmployeeService;
import com.example.nto.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean checkLogin(String login) {
        return employeeRepository.existsByLogin(login);
    }
}