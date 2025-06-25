package com.keakimleang.demoelk.books;

import com.keakimleang.demoelk.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.slf4j.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;

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
        Thread.sleep(1000);
        return rest.getForObject("https://jsonplaceholder.typicode.com/posts", String.class);
    }
}
