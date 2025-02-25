package com.bridgelabz.employee_payroll_app.service;

import com.bridgelabz.employee_payroll_app.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll_app.exception.EmployeeNotFoundException;
import com.bridgelabz.employee_payroll_app.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmployeeService implements IEmployeeService {
    private final List<Employee> employeeList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(idCounter.incrementAndGet());
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employeeList.add(employee);
        return employee;
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeList.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeList);
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = getEmployeeById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        return employee;
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeList.removeIf(employee -> employee.getId().equals(id))) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
    }
}
