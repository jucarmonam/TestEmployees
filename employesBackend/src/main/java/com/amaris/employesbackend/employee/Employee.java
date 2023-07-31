package com.amaris.employesbackend.employee;

import com.amaris.employesbackend.business.Business;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Implementing Lombok to avoid repetitive code
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Business {
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

    @Override
    public void calculateAnnualSalary() {
        anualSalary = salary * 12;
    }
}
