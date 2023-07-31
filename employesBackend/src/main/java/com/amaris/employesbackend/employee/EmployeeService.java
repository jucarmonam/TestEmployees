package com.amaris.employesbackend.employee;

import com.amaris.employesbackend.business.AnnualSalaryCalculator;
import org.springframework.stereotype.Service;

import javax.ejb.EJB;

@Service
public class EmployeeService {
    @EJB
    private AnnualSalaryCalculator annualSalaryCalculator;

    public int getAnnualSalary(int monthlySalary) {
        return annualSalaryCalculator.calculateAnnualSalary(monthlySalary);
    }
}
