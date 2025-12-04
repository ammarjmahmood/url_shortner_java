package com.urlshortener.controller;

import com.urlshortener.model.UrlMapping;
import com.urlshortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UrlController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("urls", urlShortenerService.getAllUrls());
        return "index";
    }

    @PostMapping("/api/shorten")
    @ResponseBody
    public ResponseEntity<Map<String, String>> shortenUrl(@RequestBody Map<String, String> request,
            HttpServletRequest httpRequest) {
        String longUrl = request.get("url");

        if (longUrl == null || longUrl.trim().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "URL cannot be empty");
            return ResponseEntity.badRequest().body(error);
        }

        if (!longUrl.startsWith("http://") && !longUrl.startsWith("https://")) {
            longUrl = "https://" + longUrl;
        }

        UrlMapping mapping = urlShortenerService.shortenUrl(longUrl);

        String baseUrl = httpRequest.getScheme() + "://" +
                httpRequest.getServerName() + ":" +
                httpRequest.getServerPort();

        Map<String, String> response = new HashMap<>();
        response.put("shortCode", mapping.getShortCode());
        response.put("shortUrl", baseUrl + "/" + mapping.getShortCode());
        response.put("longUrl", mapping.getLongUrl());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shortCode}")
    public RedirectView redirect(@PathVariable String shortCode) {
        UrlMapping mapping = urlShortenerService.getLongUrl(shortCode);

        if (mapping != null) {
            return new RedirectView(mapping.getLongUrl());
        }

        return new RedirectView("/?error=notfound");
    }

    @GetMapping("/api/stats/{shortCode}")
    @ResponseBody
    public ResponseEntity<?> getStats(@PathVariable String shortCode) {
        UrlMapping mapping = urlShortenerService.getLongUrl(shortCode);

        if (mapping == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Short code not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        Map<String, Object> stats = new HashMap<>();
        stats.put("shortCode", mapping.getShortCode());
        stats.put("longUrl", mapping.getLongUrl());
        stats.put("clicks", mapping.getClickCount());
        stats.put("createdAt", mapping.getCreatedAt().toString());

        return ResponseEntity.ok(stats);
    }
}
