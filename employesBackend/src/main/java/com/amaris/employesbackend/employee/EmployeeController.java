package com.amaris.employesbackend.employee;

import com.amaris.employesbackend.api.ApiResponse;
import com.amaris.employesbackend.api.ApiResponseAll;
import com.amaris.employesbackend.errorHandler.RestTemplateCustomError;
import com.amaris.employesbackend.errorHandler.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Value("${spring.api.url}")
    private String uri;
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        String url = uri + "/employees";

        try{
            if(employeeService.getAllEmployees().size() == 0){
                callApi();
            }

            ResponseEntity<ApiResponseAll> response = restTemplate.getForEntity(url, ApiResponseAll.class);

            response.getBody().getData().forEach(Employee::calculateAnnualSalary);
            return new ResponseEntity<>(response.getBody().getData(), HttpStatus.OK);
        }catch (RestTemplateCustomError e){
            // A RestTemplateCustomError was thrown, so we return this error to the client.
            // e.getMessage() should return 'Too Many Requests' for 429 status code.
            //return new ResponseEntity<>(e.getError(), e.getStatusCode());
            return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int id) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        String url = uri + "/employee/" + id;

        try{
            if(employeeService.getAllEmployees().size() == 0){
                callApi();
            }

            ResponseEntity<ApiResponse> response = restTemplate.getForEntity(url, ApiResponse.class);

            Employee employee = response.getBody().getData();
            employee.calculateAnnualSalary();
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch (RestTemplateCustomError e){
            // A RestTemplateCustomError was thrown, so we return this error to the client.
            // e.getError() should return 'Too Many Requests' for 429 status code.
            //return new ResponseEntity<>(e.getError(), e.getStatusCode());
            return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
        }
    }

    public void callApi() {
        String url = uri + "/employees";
        ResponseEntity<ApiResponseAll> response = restTemplate.getForEntity(url, ApiResponseAll.class);
        response.getBody().getData().forEach(Employee::calculateAnnualSalary);
        response.getBody().getData().forEach(employeeService::saveOrUpdate);
    }
}