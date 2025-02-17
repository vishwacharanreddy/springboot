package com.wipro.springboot.usecase1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Endpoint to add a new employee
    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        if (employee.getRole() == null || employee.getRole().isEmpty()) {
            throw new IllegalArgumentException("Role is required");
        }
        return employeeService.saveEmployee(employee);
    }


    // Endpoint to update an employee by ID
    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails);
    }

    // Endpoint to get an employee by ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    // Endpoint to get all employees
    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}