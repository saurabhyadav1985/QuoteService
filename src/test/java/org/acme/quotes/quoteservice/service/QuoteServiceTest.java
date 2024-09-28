package org.acme.quotes.quoteservice.service;

import org.acme.quotes.quoteservice.model.Quote;
import org.acme.quotes.quoteservice.repository.QuoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class QuoteServiceTest {

    @Mock
    private QuoteRepository quoteRepository;

    @InjectMocks
    private QuoteService quoteService;

    @Test
    public void testGetAllQuotes() {
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

        List<Quote> quotes = Arrays.asList(quote1, quote2);
        Page<Quote> pagedQuotes = new PageImpl<>(quotes);

        when(quoteRepository.findAll(any(Pageable.class))).thenReturn(pagedQuotes);

        Page<Quote> result = quoteService.getAllQuotes(PageRequest.of(0, 10));
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
    }

    @Test
    public void testCreateQuote() {
        Quote quote = new Quote();
        quote.setId(1L);
        quote.setAuthor("Author One");
        quote.setContent("Quote One");
        quote.setCreatedBy("User1");

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote result = quoteService.createQuote(quote);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Quote One", result.getContent());
        assertEquals("Author One", result.getAuthor());
    }

    @Test
    public void testGetQuotesByAuthor() {

        Quote quote = new Quote();
        quote.setId(1L);
        quote.setAuthor("John Doe");
        quote.setContent("Quote One");
        quote.setCreatedBy("User1");

        Quote savedQuote = new Quote();
        savedQuote.setId(2L);
        savedQuote.setAuthor("John Doe");
        savedQuote.setContent("Quote Two");
        savedQuote.setCreatedBy("User2");

        List<Quote> quotes = Arrays.asList(quote, savedQuote);
        Page<Quote> pagedQuotes = new PageImpl<>(quotes);
        when(quoteRepository.findByAuthor(eq("John Doe"), any(Pageable.class))).thenReturn(pagedQuotes);

        Page<Quote> result = quoteService.getQuotesByAuthor("John Doe", PageRequest.of(0, 10));
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals("John Doe", result.getContent().getFirst().getAuthor());
    }
}