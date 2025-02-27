package com.bridgelabz.employee_payroll_app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class EmployeeDTO {
    private Long id;

    @NotEmpty(message = "Name is a required field and cannot be empty.")
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "Name must start with a capital letter and contain only alphabets.")
    private String name;

    private Double salary;

    @NotEmpty(message = "Gender is a required field and cannot be empty.")
    private String gender;

    private LocalDate startDate;

    private String note;

    private String profilePic;

    @NotEmpty(message = "Department is a required field and cannot be empty.")
    private List<String> department;
}
