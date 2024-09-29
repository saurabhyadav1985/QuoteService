package org.acme.quotehub.exception;

import lombok.Data;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Data
public class ApiError {

    @NonNull
    private HttpStatus status;
    @NonNull
    private String message;
}