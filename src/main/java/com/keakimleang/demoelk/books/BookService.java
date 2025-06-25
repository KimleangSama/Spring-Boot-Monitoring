package com.keakimleang.demoelk.books;

import com.keakimleang.demoelk.audits.Auditable;
import java.util.*;
import lombok.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;

    @Auditable(action = "Get all books")
    public String getAllBooks() {
        return bookRepo.findAll().toString();
    }

    @Auditable(action = "Get books")
    public List<Book> getBooks() {
        return bookRepo.findAll();
    }
}
