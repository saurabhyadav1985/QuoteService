package org.acme.quotes.quoteservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acme.quotes.quoteservice.model.Quote;
import org.acme.quotes.quoteservice.repository.QuoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class QuoteService {

    private final QuoteRepository quoteRepository;

    public Quote createQuote(Quote quote) {
        log.info("Creating quote: {}", quote);
        var createdQuote = quoteRepository.save(quote);
        log.info("Created quote: {}", createdQuote);
        return createdQuote;
    }

    public Page<Quote> getAllQuotes(Pageable pageable) {
        log.info("Fetching all quotes with pageable: {}", pageable);
        var quotes = quoteRepository.findAll(pageable);
        log.info("Fetched quotes: {}", quotes);
        return quotes;
    }

    public Page<Quote> getQuotesByAuthor(String author, Pageable pageable) {
        log.info("Fetching quotes by author: {} with pageable: {}", author, pageable);
        var quotes = quoteRepository.findByAuthor(author, pageable);
        log.info("Fetched quotes by author: {}", quotes);
        return quotes;
    }
}