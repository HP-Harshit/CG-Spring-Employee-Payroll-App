package com.bridgelabz.employee_payroll_app.controller;

import com.bridgelabz.employee_payroll_app.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll_app.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeRestController {

    private Map<Long, Employee> employeeMap = new HashMap<>();
    private long currentId = 1;

    @GetMapping("/")
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeMap.values());
    }

    @GetMapping("/get/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeMap.get(id);
    }

    @PostMapping("/create")
    public Employee createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getSalary());
        employee.setId(currentId++);
        employeeMap.put(employee.getId(), employee);
        return employee;
    }

    @PutMapping("/update")
    public Employee updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeMap.get(employeeDTO.getId());
        if (employee != null) {
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            employeeMap.put(employee.getId(), employee);
        }
        return employee;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeMap.remove(id);
    }
}
