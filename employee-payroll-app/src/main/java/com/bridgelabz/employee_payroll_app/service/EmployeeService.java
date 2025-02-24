package com.bridgelabz.employee_payroll_app.service;

import com.bridgelabz.employee_payroll_app.dto.EmployeeDTO;
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
        System.out.println("Created employee: " + employee);
        return employee;
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeList.stream().filter(employee -> employee.getId().equals(id)).findFirst();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeList);
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = getEmployeeById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            System.out.println("Updated employee: " + employee);
            return employee;
        }
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeList.removeIf(employee -> employee.getId().equals(id));
        System.out.println("Deleted employee with ID: " + id);
    }
}
