package com.hailey.book.controller;

import com.hailey.book.entity.Book;
import com.hailey.book.service.BookService;
import com.hailey.book.service.S3Service; 
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BookController {

    private final BookService bookService;
    private final S3Service s3Service;

    // 1. 모든 책 목록 가져오기 (GET)
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // 2. 책 저장하기 + 이미지 S3 업로드 (POST)
    @PostMapping("/upload")
    public Book createBook(
            @RequestParam("title") String title,
            @RequestParam("price") Integer price,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        
        String imageUrl = s3Service.uploadFile(file);

        Book book = Book.builder()
                .title(title)
                .price(price)
                .imageUrl(imageUrl)
                .build();

        return bookService.saveBook(book);
    }
}