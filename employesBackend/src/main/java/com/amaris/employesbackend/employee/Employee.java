package com.amaris.employesbackend.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Implementing Lombok to avoid repetitive code
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @JsonProperty("id")
    private int id;
    @JsonProperty("employee_name")
    private String name;
    @JsonProperty("employee_salary")
    private int salary;
    @JsonProperty("employee_anual_salary")
    private int anualSalary;
    @JsonProperty("employee_age")
    private int age;
    @JsonProperty("profile_image")
    private String image;
}
