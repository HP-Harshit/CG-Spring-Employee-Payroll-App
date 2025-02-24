package com.bridgelabz.employee_payroll_app.service;

import com.bridgelabz.employee_payroll_app.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll_app.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService implements IEmployeeService {
    private final Map<Long, Employee> employeeMap = new HashMap<>();
    private long currentId = 1;

    @Override
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(currentId++);
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employeeMap.put(employee.getId(), employee);
        System.out.println("Created employee: " + employee);
        return employee;
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return Optional.ofNullable(employeeMap.get(id));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeMap.values());
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeMap.get(id);
        if (employee != null) {
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            employeeMap.put(employee.getId(), employee);
            System.out.println("Updated employee: " + employee);
        }
        return employee;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeMap.remove(id);
        System.out.println("Deleted employee with ID: " + id);
    }
}
