package com.amaris.employesbackend.employee;

import com.amaris.employesbackend.business.Business;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Implementing Lombok to avoid repetitive code
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Business {
    @Id
    @Column
    @JsonProperty("id")
    private int id;

    @Column
    @JsonProperty("employee_name")
    private String name;

    @Column
    @JsonProperty("employee_salary")
    private int salary;

    @Column
    @JsonProperty("employee_anual_salary")
    private int anualSalary;

    @Column
    @JsonProperty("employee_age")
    private int age;

    @Column
    @JsonProperty("profile_image")
    private String image;

    @Override
    public void calculateAnnualSalary() {
        anualSalary = salary * 12;
    }
}
