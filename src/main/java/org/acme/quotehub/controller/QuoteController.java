package org.acme.quotehub.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acme.quotehub.dto.QuoteRequest;
import org.acme.quotehub.dto.QuoteResponse;
import org.acme.quotehub.service.QuoteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quotes")
@AllArgsConstructor
@Slf4j
public class QuoteController {

    private final QuoteService quoteService;

    @PostMapping
    public ResponseEntity<QuoteResponse> createQuote(@Valid @RequestBody QuoteRequest quote) {
        log.info("Entering createQuote with quote: {}", quote);
        var createdQuote = quoteService.createQuote(quote);
        log.info("Returning created quote: {}", createdQuote);
        return new ResponseEntity<>(createdQuote, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<QuoteResponse>> getAllQuotes(Pageable pageable) {
        log.info("Entering getAllQuotes with pageable: {}", pageable);
        var quotes = quoteService.getAllQuotes(pageable);
        log.info("Returning quotes: {}", quotes);
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }

    @GetMapping("/author")
    public ResponseEntity<Page<QuoteResponse>> getQuotesByAuthor(@RequestParam String author, Pageable pageable) {
        log.info("Entering getQuotesByAuthor with author: {} and pageable: {}", author, pageable);
        var quotes = quoteService.getQuotesByAuthor(author, pageable);
        log.info("Returning quotes by author: {}", quotes);
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }
}