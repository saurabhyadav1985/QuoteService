package org.acme.quotehub.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class QuoteRequest {
    @NotEmpty(message = "Author is mandatory")
    @Size(max = 100, message = "Author name must be less than 100 characters")
    private String author;

    @NotEmpty(message = "Content is mandatory")
    @Size(max = 500, message = "Content must be less than 500 characters")
    private String content;
}