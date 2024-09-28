package org.acme.quotes.quoteservice.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuoteTest {

    @Test
    void testQuote() {
        Quote quote = new Quote();
        quote.setId(1L);
        quote.setAuthor("Author Name");
        quote.setContent("This is a quote.");
        quote.setCreatedBy("User");

        assertEquals(1L, quote.getId());
        assertEquals("Author Name", quote.getAuthor());
        assertEquals("This is a quote.", quote.getContent());
        assertEquals("User", quote.getCreatedBy());
    }
}