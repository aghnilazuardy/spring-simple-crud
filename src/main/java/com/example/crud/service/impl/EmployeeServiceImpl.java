package com.example.crud.service.impl;

import org.springframework.stereotype.Service;

import com.example.crud.model.Employee;
import com.example.crud.repository.EmployeeRepository;
import com.example.crud.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Integer calculateBonus(Integer salary, Integer grade) {
        if (grade == 1) {
            return salary+(salary*10/100);
        } else if (grade == 2) {
            return salary+(salary*6/100);
        } else {
            return salary+(salary*3/100);
        }
    }
}
