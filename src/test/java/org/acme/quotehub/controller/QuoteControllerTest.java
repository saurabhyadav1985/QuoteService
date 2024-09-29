package org.acme.quotehub.controller;

import org.acme.quotehub.model.Quote;
import org.acme.quotehub.service.QuoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuoteController.class)
public class QuoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuoteService quoteService;

    @Test
    @WithMockUser()
    public void testGetAllQuotes() throws Exception {
        Quote quote = new Quote();
        quote.setId(1L);
        quote.setAuthor("Author Name");
        quote.setContent("Sample Quote");
        quote.setCreatedBy("User");

        given(quoteService.getAllQuotes(any())).willReturn(new PageImpl<>(List.of(quote)));

        mockMvc.perform(get("/api/v1/quotes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].author").value("Author Name"))
                .andExpect(jsonPath("$.content[0].content").value("Sample Quote"))
                .andExpect(jsonPath("$.content[0].createdBy").value("User"))
                .andDo(print());
    }

    @Test
    @WithMockUser()
    public void testCreateQuoteFailure() throws Exception {
        String newQuote = "{\"author\": \"Author Name\", \"content\": \"New Quote\"}";

        mockMvc.perform(post("/api/v1/quotes")
                        .contentType("application/json")
                        .content(newQuote))
                .andExpect(status().isForbidden())
                .andDo(print());
    }


    @Test
    @WithMockUser()
    public void testGetPaginatedQuotes() throws Exception {
        Quote quote = new Quote();
        quote.setId(1L);
        quote.setAuthor("Author Name");
        quote.setContent("Sample Quote");
        quote.setCreatedBy("User");

        given(quoteService.getAllQuotes(any())).willReturn(new PageImpl<>(List.of(quote)));

        mockMvc.perform(get("/api/v1/quotes?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].author").value("Author Name"))
                .andExpect(jsonPath("$.content[0].content").value("Sample Quote"))
                .andExpect(jsonPath("$.content[0].createdBy").value("User"))
                .andDo(print());
    }


    @Test
    @WithMockUser()
    public void testGetPaginatedQuotesByAuthor() throws Exception {
        Quote quote = new Quote();
        quote.setId(1L);
        quote.setAuthor("John Doe");
        quote.setContent("Sample Quote");
        quote.setCreatedBy("User");

        given(quoteService.getQuotesByAuthor(any(), any())).willReturn(new PageImpl<>(List.of(quote)));

        mockMvc.perform(get("/api/v1/quotes/author")
                        .param("author", "John Doe")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].author").value("John Doe"))
                .andExpect(jsonPath("$.content[0].content").value("Sample Quote"))
                .andExpect(jsonPath("$.content[0].createdBy").value("User"))
                .andDo(print());
    }
}


