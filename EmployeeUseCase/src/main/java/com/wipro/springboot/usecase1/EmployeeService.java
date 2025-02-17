package com.wipro.springboot.usecase1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Role Map using HashMap
    private static final Map<String, String> roleMap = new HashMap<>();

    static {
        roleMap.put("developer", "Developer");
        roleMap.put("tester", "Tester");
        roleMap.put("architect", "Architect");
    }

    public Employee saveEmployee(Employee employee) {
        String role = employee.getRole().toLowerCase();
        if (roleMap.containsKey(role)) {
            employee.setRole(roleMap.get(role));
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setName(employeeDetails.getName());
        String role = employeeDetails.getRole().toLowerCase();
        if (roleMap.containsKey(role)) {
            employee.setRole(roleMap.get(role));
        }
        return employeeRepository.save(employee);
    }
    
 // Method to retrieve all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Method to retrieve an employee by ID
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RuntimeException("Employee not found");
        }
    }
}