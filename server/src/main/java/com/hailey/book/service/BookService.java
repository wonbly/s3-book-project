package com.hailey.book.service;

import com.hailey.book.entity.Book;
import com.hailey.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    
    private final BookRepository bookRepository;

    // 모든 도서 목록 가져오기
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 도서 저장하기
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
}