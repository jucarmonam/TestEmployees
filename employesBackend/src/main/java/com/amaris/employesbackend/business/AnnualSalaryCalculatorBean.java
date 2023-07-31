package com.amaris.employesbackend.business;

import javax.ejb.Stateless;

@Stateless
public class AnnualSalaryCalculatorBean implements AnnualSalaryCalculator {
    @Override
    public int calculateAnnualSalary(int monthlySalary) {
        return monthlySalary * 12;
    }
}
