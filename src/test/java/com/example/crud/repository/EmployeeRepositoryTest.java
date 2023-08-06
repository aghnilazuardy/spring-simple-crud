package com.example.crud.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.crud.model.Employee;

@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void insert() {
        Employee employee = new Employee();
        employee.setId(1001);
        employee.setName("Jonah Bluesky");
        employee.setSalary(7563000);
        employee.setGrade(1);

        employeeRepository.save(employee);

        assertNotNull(employee.getId());
    }
}
