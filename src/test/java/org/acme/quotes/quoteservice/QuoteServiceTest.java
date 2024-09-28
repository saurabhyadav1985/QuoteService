package org.acme.quotes.quoteservice;

import org.acme.quotes.quoteservice.model.Quote;
import org.acme.quotes.quoteservice.repository.QuoteRepository;
import org.acme.quotes.quoteservice.QuoteFileReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Testcontainers
public class QuoteServiceTest {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private QuoteRepository quoteRepository;

    @Test
    public void testPersistQuotes() throws Exception {
        Path filePath = Path.of("src/test/resources/quotes");
        List<Quote> quotes = QuoteFileReader.readQuotesFromFile(filePath);

        quoteRepository.saveAll(quotes);

        List<Quote> savedQuotes = quoteRepository.findAll();
        assertThat(savedQuotes).hasSize(quotes.size());
    }
}