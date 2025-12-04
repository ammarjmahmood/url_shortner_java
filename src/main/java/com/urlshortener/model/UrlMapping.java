package com.urlshortener.model;

import java.time.LocalDateTime;

public class UrlMapping {
    private String shortCode;
    private String longUrl;
    private int clickCount;
    private LocalDateTime createdAt;

    public UrlMapping(String shortCode, String longUrl) {
        this.shortCode = shortCode;
        this.longUrl = longUrl;
        this.clickCount = 0;
        this.createdAt = LocalDateTime.now();
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void incrementClickCount() {
        this.clickCount++;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
