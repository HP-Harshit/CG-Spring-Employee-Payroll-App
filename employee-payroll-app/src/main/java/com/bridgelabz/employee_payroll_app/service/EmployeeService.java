package com.bridgelabz.employee_payroll_app.service;

import com.bridgelabz.employee_payroll_app.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll_app.exception.EmployeeNotFoundException;
import com.bridgelabz.employee_payroll_app.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
public class EmployeeService implements IEmployeeService {
    private final List<Employee> employeeList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(idCounter.incrementAndGet());
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employee.setGender(employeeDTO.getGender());
        employee.setStartDate(employeeDTO.getStartDate());
        employee.setNote(employeeDTO.getNote());
        employee.setProfilePic(employeeDTO.getProfilePic());
        employee.setDepartment(employeeDTO.getDepartment());
        employeeList.add(employee);
        log.info("Created employee with ID: " + employee.getId());
        return employee;
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        log.info("Fetching employee with ID: " + id);
        return employeeList.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees");
        return new ArrayList<>(employeeList);
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = getEmployeeById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employee.setGender(employeeDTO.getGender());
        employee.setStartDate(employeeDTO.getStartDate());
        employee.setNote(employeeDTO.getNote());
        employee.setProfilePic(employeeDTO.getProfilePic());
        employee.setDepartment(employeeDTO.getDepartment());
        log.info("Updated employee with ID: " + id);
        return employee;
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeList.removeIf(employee -> employee.getId().equals(id))) {
            log.error("Employee with ID " + id + " not found");
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
        log.info("Deleted employee with ID: " + id);
    }
}
