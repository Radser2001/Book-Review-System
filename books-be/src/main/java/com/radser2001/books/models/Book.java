package com.radser2001.books.models;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// let Spring Boot know that this class is a MongoDB document
@Document(collection = "books")

// take care of the getters and setters for us
@Data
// create a constructor with all the attributes
@AllArgsConstructor
// create a constructor with no attributes
@NoArgsConstructor
public class Book {

    // let the MongoDB generate the unique ID for the document
    @Id
    private ObjectId id;
    private String title;
    private String isbn;
    private int pageCount;
    private Date publishedDate;
    private String thumbnailUrl;
    private String shortDescription;
    private String longDescription;
    private String status;
    private List<String> authors;
    private List<String> categories;

    // let Spring Boot know that this field is a reference to another document
    @DocumentReference
    private List<Review> reviewIds;

}
