package org.acme.quotes.quoteservice.repository;

import org.acme.quotes.quoteservice.model.Quote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class QuoteRepositoryTest {

    @Autowired
    private QuoteRepository quoteRepository;

    @BeforeEach
    void setUp() {
        quoteRepository.deleteAll();
    }

    @Test
    void testCreateQuote() {
        Quote quote = new Quote();
        quote.setId(1L);
        quote.setAuthor("Author Name");
        quote.setContent("This is a quote.");
        quote.setCreatedBy("User");

        Quote savedQuote = quoteRepository.save(quote);

        assertNotNull(savedQuote.getId());
        assertEquals("Author Name", savedQuote.getAuthor());
        assertEquals("This is a quote.", savedQuote.getContent());
        assertEquals("User", savedQuote.getCreatedBy());
    }

    @Test
    void testGetAllQuotesPaginated() {
        Quote quote1 = new Quote();
        quote1.setId(1L);
        quote1.setAuthor("Author One");
        quote1.setContent("Quote One");
        quote1.setCreatedBy("User1");

        Quote quote2 = new Quote();
        quote2.setId(2L);
        quote2.setAuthor("Author One");
        quote2.setContent("Quote Two");
        quote2.setCreatedBy("User2");

        quoteRepository.save(quote1);
        quoteRepository.save(quote2);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Quote> quotesPage = quoteRepository.findAll(pageable);

        assertEquals(2, quotesPage.getTotalElements());
        assertEquals(2, quotesPage.getContent().size());
    }

    @Test
    void testGetQuotesByAuthorPaginated() {
        Quote quote1 = new Quote();
        quote1.setId(1L);
        quote1.setAuthor("Author One");
        quote1.setContent("Quote One");
        quote1.setCreatedBy("User1");

        Quote quote2 = new Quote();
        quote2.setId(2L);
        quote2.setAuthor("Author One");
        quote2.setContent("Quote Two");
        quote2.setCreatedBy("User2");

        Quote quote3 = new Quote();
        quote3.setId(3L);
        quote3.setAuthor("Author Two");
        quote3.setContent("Quote Three");
        quote3.setCreatedBy("User3");

        quoteRepository.save(quote1);
        quoteRepository.save(quote2);
        quoteRepository.save(quote3);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Quote> quotesPage = quoteRepository.findByAuthor("Author One", pageable);

        assertEquals(2, quotesPage.getTotalElements());
        assertEquals(2, quotesPage.getContent().size());
    }
}