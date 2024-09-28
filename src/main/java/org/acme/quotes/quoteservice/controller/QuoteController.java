package org.acme.quotes.quoteservice.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
public class QuoteController {

    private QuoteService quoteService;

    @PostMapping
    public ResponseEntity<Quote> createQuote(@Valid @RequestBody Quote quote) {
        quote.setCreatedBy(SecurityUtils.getAuthenticatedUser());
        var createdQuote = quoteService.createQuote(quote);
        return new ResponseEntity<>(createdQuote, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Quote>> getAllQuotes(Pageable pageable) {
        var quotes = quoteService.getAllQuotes(pageable);
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }

    @GetMapping("/author")
    public ResponseEntity<Page<Quote>> getQuotesByAuthor(@RequestParam String author, Pageable pageable) {
        var quotes = quoteService.getQuotesByAuthor(author, pageable);
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }

}