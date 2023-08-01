package com.amaris.employesbackend.employeeTests;

import com.amaris.employesbackend.employee.Employee;
import com.amaris.employesbackend.employee.EmployeeController;
import com.amaris.employesbackend.employee.EmployeeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {
    @LocalServerPort
    private int port;

    private String url;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        url = String.format("http://localhost:%d/employees", port);
    }

    @Test
    @DisplayName("/employees rest api test ")
    public void testGetAllEmployees() {
        Employee employee1 = new Employee(1, "Tiger Nixon", 320800,3849600,61,"");
        Employee employee2 = new Employee(2, "Garrett Winters", 170750,2049000,63,"");

        ResponseEntity<List<Employee>> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        assertThat(result.getBody().size()).isEqualTo(24);
        assertThat(result.getBody().get(0)).isEqualTo(employee1);
        assertThat(result.getBody().get(1)).isEqualTo(employee2);
    }

    @Test
    @DisplayName("/employees rest api test ID")
    public void testGetEmployeeById() {
        Employee employee1 = new Employee(1, "Tiger Nixon", 320800,3849600,61,"");

        ResponseEntity<Employee> result = restTemplate.getForEntity(url + "/1", Employee.class);

        assertThat(result.getBody()).isEqualTo(employee1);
    }
}
