package com.radser2001.books.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radser2001.books.models.Book;
import com.radser2001.books.repositories.BookRepository;

// let Spring Boot know that this class is a service
@Service
public class BookService {

    // let Spring Boot know that this attribute needs to be instantiated
    @Autowired
    private BookRepository bookRepository;

    // database access methods
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    // public Optional<Book> getBookById(ObjectId id) {
    // return bookRepository.findById(id);
    // }

    public Optional<Book> findBookByISBNId(String isbn) {
        return bookRepository.findBookByisbn(isbn);
    }
}
