package com.example.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

import com.example.crud.model.Employee;
import com.example.crud.repository.EmployeeRepository;
import com.example.crud.service.EmployeeService;

import jakarta.validation.Valid;

@Controller
public class CrudController {
    
    private EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    public CrudController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/index")
    public String showUserList(Model model) {        
        model.addAttribute("employees", employeeRepository.findAll());
        return "index";
    }

    @GetMapping("/view/all")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/view/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Integer employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow();
    }

    // @GetMapping("/view")
    public Employee getAllEmployeesWithBonus() {
        List<Employee> employees = this.getAllEmployees();
        Employee result = new Employee();
        for (Employee employee : employees) {
                result.setId(employee.getId());
                result.setName(employee.getName());
                result.setSalary(employee.getSalary());
                result.setGrade(employee.getGrade());
                // result.setBonus(employeeService.calculateBonus(employee.getSalary(), employee.getGrade()));
        }
        return result;
    }

    @PostMapping("/add")
    public Employee addEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PostMapping("/edit/{id}")
    public Employee editEmployee(@PathVariable(value = "id") Integer employeeId, @Valid @RequestBody Employee updatedData) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow();

        employee.setId(updatedData.getId());
        employee.setName(updatedData.getName());
        employee.setSalary(updatedData.getSalary());
        employee.setGrade(updatedData.getGrade());

        Employee updatedEmployee = employeeService.saveEmployee(employee);
        return updatedEmployee;
    }
}
