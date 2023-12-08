package com.radser2001.books.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// let Spring Boot know that this class is a MongoDB document
@Document(collection = "reviews")

// take care of the getters and setters for us
@Data
// create a constructor with all the attributes
@AllArgsConstructor
// create a constructor with no attributes
@NoArgsConstructor
public class Review {

    // let the MongoDB generate the unique ID for the document
    @Id
    private ObjectId id;
    private String body;
    private String isbn;

    public Review(String body, String isbn) {
        this.body = body;
        this.isbn = isbn;
    }

}
