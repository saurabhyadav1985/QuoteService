package org.acme.quotehub;

import org.acme.quotehub.model.Quote;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
public class QuoteHubIntegrationTest {

    @SuppressWarnings("resource")
    @Container
    public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${app.security.admin.username}")
    private String username;

    @Value("${app.security.admin.password}")
    private String password;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");
        registry.add("spring.jpa.database-platform", () -> "org.hibernate.dialect.PostgreSQLDialect");
    }


    @Test
    public void testCreateAndGetQuote() {
        String newQuote = """
                    {
                        "content": "From now on, April 1st is now March 32nd.",
                        "author": "Dean Pelton"
                    }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(username, password);
        HttpEntity<String> request = new HttpEntity<>(newQuote, headers);

        ResponseEntity<String> createResponse = restTemplate.postForEntity("/api/v1/quotes", request, String.class);
        assertThat(createResponse.getStatusCode().is2xxSuccessful()).isTrue();

        ResponseEntity<PageImpl<Quote>> getResponse = restTemplate.exchange(
                "/api/v1/quotes",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                }
        );
        assertThat(getResponse.getStatusCode().is2xxSuccessful()).isTrue();

        var quotes = getResponse.getBody();
        assertThat(quotes).isNotEmpty();
        assertThat(quotes.getContent().getFirst().getContent()).isEqualTo("From now on, April 1st is now March 32nd.");
        assertThat(quotes.getContent().getFirst().getAuthor()).isEqualTo("Dean Pelton");
    }

}