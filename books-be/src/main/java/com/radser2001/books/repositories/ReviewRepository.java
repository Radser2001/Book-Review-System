package com.radser2001.books.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.radser2001.books.models.Review;

// Spring Data MongoDB creates an implementation of this interface automatically
@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {

}
