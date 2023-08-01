package com.amaris.employesbackend.employee;

import com.amaris.employesbackend.business.AnnualSalaryCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ejb.EJB;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    /*
    @EJB
    private AnnualSalaryCalculator annualSalaryCalculator;

    public int getAnnualSalary(int monthlySalary) {
        return annualSalaryCalculator.calculateAnnualSalary(monthlySalary);
    }
     */
    @Autowired
    EmployeeRepository repository;
    public Employee getEmployeeById(int id) {
        return repository.findById(id).get();
    }
    public List<Employee> getAllEmployees(){
        List<Employee> employees = new ArrayList<>();
        repository.findAll().forEach(employees::add);
        return employees;
    }
    public void saveOrUpdate(Employee employee) {
        repository.save(employee);
    }
}
