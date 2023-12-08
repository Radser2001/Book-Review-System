package com.radser2001.books.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.radser2001.books.models.Book;
import com.radser2001.books.services.BookService;

// let Spring Boot know that this class is a REST controller
@RestController
// let Spring Boot know that all the endpoints in this controller will start
// with /api/v1/books
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/books")
public class BookController {

    // let Spring Boot know that this attribute needs to be instantiated
    @Autowired
    private BookService bookService;

    // Get all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<List<Book>>(bookService.allBooks(), HttpStatus.OK);
    }

    // Get a book by id
    // @GetMapping("/{id}")
    // public ResponseEntity<Optional<Book>> getBookById(@PathVariable("id")
    // ObjectId id) {
    // return new ResponseEntity<Optional<Book>>(bookService.getBookById(id),
    // HttpStatus.OK);
    // }

    // Get a book by ISBN id
    @GetMapping("/{isbn}")
    public ResponseEntity<Optional<Book>> findBookByISBNId(@PathVariable("isbn") String isbn) {
        return new ResponseEntity<Optional<Book>>(bookService.findBookByISBNId(isbn), HttpStatus.OK);
    }
}
