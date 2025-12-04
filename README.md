# URL Shortener

A simple URL Shortener Service built with Spring Boot.

## Features

- Shorten long URLs to 6-character short codes.
- Redirect from short codes to original URLs.
- Track click statistics for each shortened URL.
- Simple and clean web interface.
- REST API for shortening and retrieving stats.

## Technologies Used

- Java 17
- Spring Boot 3.2.0
- Spring Web
- Spring Thymeleaf
- Maven

## Project Structure

```
url-shortener/
├── pom.xml                                      (Maven configuration)
├── src/
│   └── main/
│       ├── java/com/urlshortener/
│       │   ├── UrlShortenerApplication.java    (Main entry point)
│       │   ├── controller/
│       │   │   └── UrlController.java          (API endpoints)
│       │   ├── service/
│       │   │   └── UrlShortenerService.java    (Business logic)
│       │   └── model/
│       │       └── UrlMapping.java             (Data model)
│       └── resources/
│           ├── application.properties           (Configuration)
│           └── templates/
│               └── index.html                   (Web interface)
```

## How to Run

### Prerequisites

- Java 17 or higher
- Maven

### Running Locally

1.  Clone the repository:
    ```bash
    git clone https://github.com/ammarjmahmood/url_shortner_java.git
    cd url_shortner_java
    ```

2.  Build the project:
    ```bash
    mvn clean install
    ```

3.  Run the application:
    ```bash
    mvn spring-boot:run
    ```

4.  Open your browser and navigate to `http://localhost:8080`.

## API Endpoints

### Shorten URL

-   **URL:** `/api/shorten`
-   **Method:** `POST`
-   **Body:**
    ```json
    {
      "url": "https://www.example.com"
    }
    ```
-   **Response:**
    ```json
    {
      "shortCode": "abc123",
      "shortUrl": "http://localhost:8080/abc123",
      "longUrl": "https://www.example.com"
    }
    ```

### Get Statistics

-   **URL:** `/api/stats/{shortCode}`
-   **Method:** `GET`
-   **Response:**
    ```json
    {
      "shortCode": "abc123",
      "longUrl": "https://www.example.com",
      "clicks": 5,
      "createdAt": "2023-10-27T10:00:00"
    }
    ```

## Deployment

This application can be easily deployed to platforms like Railway, Render, or Heroku.

### Railway

1.  Sign up at [Railway](https://railway.app).
2.  Install Railway CLI: `npm install -g @railway/cli`
3.  Login and deploy:
    ```bash
    railway login
    railway init
    railway up
    ```

## License

This project is open source.
