package org.acme.quotes.quoteservice;

import org.acme.quotes.quoteservice.model.Quote;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class QuoteFileReader {

    public static List<Quote> readQuotesFromFile(Path filePath) throws IOException {
        List<Quote> quotes = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            String[] parts = line.split(": ", 2);
            if (parts.length == 2) {
                Quote quote = new Quote();
                quote.setAuthor(parts[0].trim());
                quote.setContent(parts[1].trim());
                quotes.add(quote);
            }
        }

        return quotes;
    }
}