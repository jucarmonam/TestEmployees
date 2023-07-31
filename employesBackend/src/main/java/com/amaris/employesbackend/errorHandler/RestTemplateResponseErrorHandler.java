package com.amaris.employesbackend.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import javax.ws.rs.NotFoundException;
import java.io.IOException;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return (
                httpResponse.getStatusCode().is4xxClientError()
                        || httpResponse.getStatusCode().is5xxServerError());
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {

        if (httpResponse.getStatusCode().is5xxServerError()) {
            // handle SERVER_ERROR
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new RestTemplateCustomError(httpResponse.getStatusCode(), "Not Found");
            }else if(httpResponse.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR){
                throw new RestTemplateCustomError(httpResponse.getStatusCode(), "Internal Server Error");
            }else if(httpResponse.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE){
                throw new RestTemplateCustomError(httpResponse.getStatusCode(), "Service Unavailable");
            }else if(httpResponse.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS){
                throw new RestTemplateCustomError(httpResponse.getStatusCode(), "Too Many Requests");
            }
        } else if (httpResponse.getStatusCode().is4xxClientError()) {
            // handle CLIENT_ERROR
            if (httpResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new RestTemplateCustomError(httpResponse.getStatusCode(), "Bad Request");
            }else if(httpResponse.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS){
                throw new RestTemplateCustomError(httpResponse.getStatusCode(), "Too Many Requests");
            }else if(httpResponse.getStatusCode() == HttpStatus.UNAUTHORIZED){
                throw new RestTemplateCustomError(httpResponse.getStatusCode(), "Unauthorized");
            }else if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException();
            }
        }
    }
}
