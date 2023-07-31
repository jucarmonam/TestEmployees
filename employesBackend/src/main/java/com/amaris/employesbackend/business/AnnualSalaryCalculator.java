package com.amaris.employesbackend.business;

import javax.ejb.Local;

@Local
public interface AnnualSalaryCalculator {
    int calculateAnnualSalary(int monthlySalary);
}
