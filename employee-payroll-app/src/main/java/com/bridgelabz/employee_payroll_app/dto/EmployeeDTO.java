package com.bridgelabz.employee_payroll_app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeDTO {
    private Long id;

    @NotEmpty(message = "Name is a required field and cannot be empty.")
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "Name must start with a capital letter and contain only alphabets.")
    private String name;

    private Double salary;
}
