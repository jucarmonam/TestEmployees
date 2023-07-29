package com.amaris.employesbackend.api;

import com.amaris.employesbackend.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {
    @JsonProperty("status")
    private String status;
    @JsonProperty("data")
    private Employee data;
    @JsonProperty("message")
    private String message;
}
