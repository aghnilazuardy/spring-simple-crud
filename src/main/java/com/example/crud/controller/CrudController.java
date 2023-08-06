package com.example.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    // show index page
    @GetMapping(value = "/index")
    public String showUserList(Model model) {        
        model.addAttribute("employees", employeeRepository.findAll());
        return "index";
    }

    // add employee page
    @GetMapping("/signup")
    public String showSignUpForm(Employee employee) {
        return "add-user";
    }

    // edit employee page
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        
        model.addAttribute("employee", employee);
        return "update-user";
    }

    // add employee service
    @PostMapping(value = "/add")
    public String addUser(@Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        
        employeeService.saveEmployee(employee);
        return "redirect:/index";
    }

    // api routes get all employee
    @GetMapping("/view/all")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // api routes get employee by id
    @GetMapping("/view/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Integer employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow();
    }

    // api routes get all employee data with bonus
    @GetMapping("/view/bonus")
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

    // api routes edit employee
    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable(value = "id") Integer id, 
        @Valid Employee updatedData, 
        BindingResult result, Model model) {
        if (result.hasErrors()) {
            updatedData.setId(id);
            return "update-user";
        }

        employeeService.saveEmployee(updatedData);
        return "redirect:/index";
    }
}
