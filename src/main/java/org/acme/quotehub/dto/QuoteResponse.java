package org.acme.quotehub.dto;

import lombok.Data;

@Data
public class QuoteResponse {
    private Long id;
    private String author;
    private String content;
    private String createdBy;
}