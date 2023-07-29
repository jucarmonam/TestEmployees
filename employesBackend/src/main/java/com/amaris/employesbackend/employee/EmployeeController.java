package com.amaris.employesbackend.employee;

import com.amaris.employesbackend.api.ApiResponse;
import com.amaris.employesbackend.api.ApiResponseAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Value("${spring.datasource.url}")
    private String url;
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        url = url + "/employees";

        ResponseEntity<ApiResponseAll> response = restTemplate.getForEntity(url, ApiResponseAll.class);

        return new ResponseEntity<>(response.getBody().getData(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        url = url + "/employee/" + id;

        ResponseEntity<ApiResponse> response = restTemplate.getForEntity(url, ApiResponse.class);

        Employee employee = response.getBody().getData();
        employee.setAnualSalary(employeeService.calculateAnnualSalary(employee.getSalary()));

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}