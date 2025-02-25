package com.bridgelabz.employee_payroll_app.service;

import com.bridgelabz.employee_payroll_app.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll_app.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
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
        log.info("Created employee: {}", employee);
        return employee;
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        Optional<Employee> employee = employeeList.stream().filter(e -> e.getId().equals(id)).findFirst();
        log.info("Retrieved employee with ID {}: {}", id, employee.orElse(null));
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        log.info("Retrieving all employees");
        return new ArrayList<>(employeeList);
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = getEmployeeById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            log.info("Updated employee: {}", employee);
            return employee;
        }
        log.warn("Employee with ID {} not found", id);
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {
        if (employeeList.removeIf(employee -> employee.getId().equals(id))) {
            log.info("Deleted employee with ID: {}", id);
        } else {
            log.warn("Failed to delete employee with ID: {}", id);
        }
    }
}
