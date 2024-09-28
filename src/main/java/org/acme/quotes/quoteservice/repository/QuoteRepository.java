package org.acme.quotes.quoteservice.repository;

import org.acme.quotes.quoteservice.model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    Page<Quote> findByAuthor(String author, Pageable pageable);
}