package com.example.crud.service;

import com.example.crud.model.Employee;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    Integer calculateBonus(Integer salary, Integer grade);
}
