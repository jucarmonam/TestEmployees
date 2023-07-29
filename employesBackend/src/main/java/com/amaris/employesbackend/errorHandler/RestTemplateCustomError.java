package com.amaris.employesbackend.errorHandler;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Data
@NoArgsConstructor
public class RestTemplateCustomError extends RuntimeException {

    private HttpStatusCode statusCode;
    private String error;

    public RestTemplateCustomError(HttpStatusCode statusCode, String error) {
        super(error);
        this.statusCode = statusCode;
        this.error = error;
    }
}
