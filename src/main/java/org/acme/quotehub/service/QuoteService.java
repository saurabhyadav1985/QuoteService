package org.acme.quotehub.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acme.quotehub.exception.QuoteHubException;
import org.acme.quotehub.model.Quote;
import org.acme.quotehub.repository.QuoteRepository;
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
        try {
            var createdQuote = quoteRepository.save(quote);
            log.info("Created quote: {}", createdQuote);
            return createdQuote;
        } catch (Exception e) {
            log.error("Error creating quote: {}", e.getMessage());
            throw new QuoteHubException("Failed to create quote", e);
        }
    }

    public Page<Quote> getAllQuotes(Pageable pageable) {
        log.info("Fetching all quotes with pageable: {}", pageable);
        try {
            var quotes = quoteRepository.findAll(pageable);
            log.info("Fetched quotes: {}", quotes);
            return quotes;
        } catch (Exception e) {
            log.error("Error fetching all quotes: {}", e.getMessage());
            throw new QuoteHubException("Failed to fetch quotes", e);
        }
    }

    public Page<Quote> getQuotesByAuthor(String author, Pageable pageable) {
        log.info("Fetching quotes by author: {} with pageable: {}", author, pageable);
        try {
            var quotes = quoteRepository.findByAuthor(author, pageable);
            log.info("Fetched quotes by author: {}", quotes);
            return quotes;
        } catch (Exception e) {
            log.error("Error fetching quotes by author: {}", e.getMessage());
            throw new QuoteHubException("Failed to fetch quotes by author", e);
        }
    }
}