package org.acme.quotes.quoteservice.service;

import lombok.AllArgsConstructor;
import org.acme.quotes.quoteservice.model.Quote;
import org.acme.quotes.quoteservice.repository.QuoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuoteService {

    private QuoteRepository quoteRepository;

    public Quote createQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    public Page<Quote> getAllQuotes(Pageable pageable) {
        return quoteRepository.findAll(pageable);
    }

    public Page<Quote> getQuotesByAuthor(String author, Pageable pageable) {
        return quoteRepository.findByAuthor(author, pageable);
    }
}