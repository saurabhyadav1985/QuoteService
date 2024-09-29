package org.acme.quotes.quoteservice.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acme.quotes.quoteservice.model.Quote;
import org.acme.quotes.quoteservice.service.QuoteService;
import org.acme.quotes.quoteservice.util.SecurityUtils;
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
    public ResponseEntity<Quote> createQuote(@Valid @RequestBody Quote quote) {
        log.info("Entering createQuote with quote: {}", quote);
        quote.setCreatedBy(SecurityUtils.getAuthenticatedUser());
        var createdQuote = quoteService.createQuote(quote);
        log.info("Returning created quote: {}", createdQuote);
        return new ResponseEntity<>(createdQuote, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Quote>> getAllQuotes(Pageable pageable) {
        log.info("Entering getAllQuotes with pageable: {}", pageable);
        var quotes = quoteService.getAllQuotes(pageable);
        log.info("Returning quotes: {}", quotes);
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }

    @GetMapping("/author")
    public ResponseEntity<Page<Quote>> getQuotesByAuthor(@RequestParam String author, Pageable pageable) {
        log.info("Entering getQuotesByAuthor with author: {} and pageable: {}", author, pageable);
        var quotes = quoteService.getQuotesByAuthor(author, pageable);
        log.info("Returning quotes by author: {}", quotes);
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }
}