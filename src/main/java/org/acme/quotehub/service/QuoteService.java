package org.acme.quotehub.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acme.quotehub.dto.QuoteRequest;
import org.acme.quotehub.dto.QuoteResponse;
import org.acme.quotehub.exception.QuoteHubException;
import org.acme.quotehub.model.Quote;
import org.acme.quotehub.repository.QuoteRepository;
import org.acme.quotehub.util.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class QuoteService {

    private final QuoteRepository quoteRepository;

    public QuoteResponse createQuote(QuoteRequest quoteRequest) {
        log.info("Creating quote: {}", quoteRequest);
        Quote quote = new Quote();
        quote.setAuthor(quoteRequest.getAuthor());
        quote.setContent(quoteRequest.getContent());
        quote.setCreatedBy(SecurityUtils.getAuthenticatedUser());
        try {
            var createdQuote = quoteRepository.save(quote);
            log.info("Created quote: {}", createdQuote);
            return mapToQuoteResponse(createdQuote);
        } catch (Exception e) {
            log.error("Error creating quote: {}", e.getMessage());
            throw new QuoteHubException("Failed to create quote", e);
        }
    }

    public Page<QuoteResponse> getAllQuotes(Pageable pageable) {
        log.info("Fetching all quotes with pageable: {}", pageable);
        try {
            var quotes = quoteRepository.findAll(pageable).map(this::mapToQuoteResponse);
            log.info("Fetched quotes: {}", quotes);
            return quotes;
        } catch (Exception e) {
            log.error("Error fetching all quotes: {}", e.getMessage());
            throw new QuoteHubException("Failed to fetch quotes", e);
        }
    }

    public Page<QuoteResponse> getQuotesByAuthor(String author, Pageable pageable) {
        log.info("Fetching quotes by author: {} with pageable: {}", author, pageable);
        try {
            var quotes = quoteRepository.findByAuthor(author, pageable).map(this::mapToQuoteResponse);
            log.info("Fetched quotes by author: {}", quotes);
            return quotes;
        } catch (Exception e) {
            log.error("Error fetching quotes by author: {}", e.getMessage());
            throw new QuoteHubException("Failed to fetch quotes by author", e);
        }
    }

    private QuoteResponse mapToQuoteResponse(Quote quote) {
        QuoteResponse quoteResponse = new QuoteResponse();
        quoteResponse.setId(quote.getId());
        quoteResponse.setAuthor(quote.getAuthor());
        quoteResponse.setContent(quote.getContent());
        quoteResponse.setCreatedBy(quote.getCreatedBy());
        return quoteResponse;
    }
}