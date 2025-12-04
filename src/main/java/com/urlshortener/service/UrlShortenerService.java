package com.urlshortener.service;

import com.urlshortener.model.UrlMapping;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UrlShortenerService {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_CODE_LENGTH = 6;

    private final Map<String, UrlMapping> urlDatabase = new HashMap<>();
    private final Random random = new Random();

    public UrlMapping shortenUrl(String longUrl) {
        String shortCode = generateShortCode();

        while (urlDatabase.containsKey(shortCode)) {
            shortCode = generateShortCode();
        }

        UrlMapping mapping = new UrlMapping(shortCode, longUrl);
        urlDatabase.put(shortCode, mapping);

        return mapping;
    }

    public UrlMapping getLongUrl(String shortCode) {
        UrlMapping mapping = urlDatabase.get(shortCode);
        if (mapping != null) {
            mapping.incrementClickCount();
        }
        return mapping;
    }

    public Map<String, UrlMapping> getAllUrls() {
        return new HashMap<>(urlDatabase);
    }

    private String generateShortCode() {
        StringBuilder shortCode = new StringBuilder(SHORT_CODE_LENGTH);
        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            shortCode.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return shortCode.toString();
    }

    public boolean exists(String shortCode) {
        return urlDatabase.containsKey(shortCode);
    }
}
