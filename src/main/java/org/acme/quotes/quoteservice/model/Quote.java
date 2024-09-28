package org.acme.quotes.quoteservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty(message = "Author is mandatory")
    @Size(max = 100, message = "Author name must be less than 100 characters")
    String author;

    @NotEmpty(message = "Content is mandatory")
    @Size(max = 500, message = "Content must be less than 500 characters")
    String content;

    @Size(max = 100, message = "Created by must be less than 100 characters")
    String createdBy;
}
