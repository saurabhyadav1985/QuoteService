package org.acme.quotes.quoteservice.exception;

import lombok.AllArgsConstructor;
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