package com.keakimleang.demoelk.books;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookRepo bookRepo;
    private final RestTemplate rest;

    @GetMapping("/books")
    public String getBooks() {
        log.info("Getting books");
        log.info("traceId: {}", MDC.get("traceId"));
        bookService.getBooks();
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable Long id) throws InterruptedException {
        Thread.sleep(1000);
        return bookRepo.findById(id).map(Book::toString).orElse("Book not found");
    }

    @GetMapping("/books/external")
    public String getBookByIdExternal() throws InterruptedException {
        HttpResponse<String> response = Unirest.post("https://uat-api-nginx.amkcambodia.com:8065/api/v1/common/oauth/jwt/generate")
                .header("client_id", "e23e169d-64ea-495b-a04e-9e72bc4cdee4")
                .multiPartContent()
                .asString();
        log.info(response.getBody());
        return rest.getForObject("https://jsonplaceholder.typicode.com/posts", String.class);
    }
}
