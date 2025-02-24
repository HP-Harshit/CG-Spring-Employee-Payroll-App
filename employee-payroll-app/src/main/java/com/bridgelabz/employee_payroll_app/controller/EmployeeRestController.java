package com.bridgelabz.employee_payroll_app.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeRestController {

    @GetMapping("/")
    public String getAllEmployees() {
        return "Getting all employees";
    }

    @GetMapping("/get/{id}")
    public String getEmployeeById(@PathVariable Long id) {
        return "Getting employee with ID: " + id;
    }

    @PostMapping("/create")
    public String createEmployee(@RequestBody String employee) {
        return "Creating employee: " + employee;
    }

    @PutMapping("/update")
    public String updateEmployee(@RequestBody String employee) {
        return "Updating employee: " + employee;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        return "Deleting employee with ID: " + id;
    }
}
