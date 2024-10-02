package org.acme.quotehub.service;

import org.acme.quotehub.dto.QuoteRequest;
import org.acme.quotehub.dto.QuoteResponse;
import org.acme.quotehub.model.Quote;
import org.acme.quotehub.repository.QuoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

        Page<QuoteResponse> result = quoteService.getAllQuotes(PageRequest.of(0, 10));
        assertNotNull(result);
        assertEquals(2, result.getContent().size());

        QuoteResponse response1 = result.getContent().get(0);
        assertEquals(1L, response1.getId());
        assertEquals("Author One", response1.getAuthor());
        assertEquals("Quote One", response1.getContent());
        assertEquals("User1", response1.getCreatedBy());

        QuoteResponse response2 = result.getContent().get(1);
        assertEquals(2L, response2.getId());
        assertEquals("Author One", response2.getAuthor());
        assertEquals("Quote Two", response2.getContent());
        assertEquals("User2", response2.getCreatedBy());
    }

    @Test
    public void testCreateQuote() {
        var quoteRequest = new QuoteRequest();
        quoteRequest.setAuthor("Author One");
        quoteRequest.setContent("Quote One");

        var quote = new Quote();
        quote.setId(1L);
        quote.setAuthor("Author One");
        quote.setContent("Quote One");
        quote.setCreatedBy("Admin");

        when(quoteRepository.save(any(Quote.class))).thenReturn(quote);

        var result = quoteService.createQuote(quoteRequest);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Author One", result.getAuthor());
        assertEquals("Quote One", result.getContent());
        assertEquals("Admin", result.getCreatedBy());
    }

    @Test
    public void testGetQuotesByAuthor() {
        Quote quote1 = new Quote();
        quote1.setId(1L);
        quote1.setAuthor("John Doe");
        quote1.setContent("Quote One");
        quote1.setCreatedBy("User1");

        Quote quote2 = new Quote();
        quote2.setId(2L);
        quote2.setAuthor("John Doe");
        quote2.setContent("Quote Two");
        quote2.setCreatedBy("User2");

        List<Quote> quotes = Arrays.asList(quote1, quote2);
        Page<Quote> pagedQuotes = new PageImpl<>(quotes);

        when(quoteRepository.findByAuthor(eq("John Doe"), any(Pageable.class))).thenReturn(pagedQuotes);

        Page<QuoteResponse> result = quoteService.getQuotesByAuthor("John Doe", PageRequest.of(0, 10));
        assertNotNull(result);
        assertEquals(2, result.getContent().size());

        QuoteResponse response1 = result.getContent().get(0);
        assertEquals(1L, response1.getId());
        assertEquals("John Doe", response1.getAuthor());
        assertEquals("Quote One", response1.getContent());
        assertEquals("User1", response1.getCreatedBy());

        QuoteResponse response2 = result.getContent().get(1);
        assertEquals(2L, response2.getId());
        assertEquals("John Doe", response2.getAuthor());
        assertEquals("Quote Two", response2.getContent());
        assertEquals("User2", response2.getCreatedBy());
    }
}