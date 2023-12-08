package com.radser2001.books.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.radser2001.books.models.Book;

// let Spring Boot know that this interface is a repository
@Repository
public interface BookRepository extends MongoRepository<Book, ObjectId> {

    Optional<Book> findBookByisbn(String isbn);

}
