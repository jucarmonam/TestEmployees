package com.amaris.employesbackend.employee;

import javax.ejb.Local;

@Local
public interface AnnualSalaryCalculator {
    int calculateAnnualSalary(int monthlySalary);
}
