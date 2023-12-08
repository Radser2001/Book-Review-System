package com.radser2001.books.services;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.radser2001.books.models.Book;
import com.radser2001.books.models.Review;
import com.radser2001.books.repositories.ReviewRepository;

// Spring Boot will automatically scan the classpath for this annotation
@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    // create a review
    public Review createReview(String reviewBody, String isbn) {
        Review review = reviewRepository.insert(new Review(reviewBody, isbn));

        mongoTemplate.update(Book.class).matching(Criteria.where("isbn").is(isbn))
                .apply(new Update().push("reviewIds").value(review)).first();
        return review;
    }

    // update a review
    public Review updateReview(String id, String reviewBody, String isbn) {
        // Find the review by id
        Optional<Review> existingReviewOptional = reviewRepository.findById(new ObjectId(id));

        // If the review exists, update the body and save it
        if (existingReviewOptional.isPresent()) {
            Review existingReview = existingReviewOptional.get();
            existingReview.setBody(reviewBody);
            Review updatedReview = reviewRepository.save(existingReview);

            // Update the review in the associated book's reviewIds list
            mongoTemplate.update(Book.class)
                    .matching(Criteria.where("isbn").is(isbn).and("reviewIds").in(existingReview.getId()))
                    .apply(new Update().set("reviewIds.$", updatedReview.getId()))
                    .first();

            return updatedReview;
        } else {
            return null; // Review not found
        }
    }

    // delete a review
    public Review deleteReview(String id) {
        // find the review by id
        Optional<Review> existingReviewOptional = reviewRepository.findById(new ObjectId(id));

        // if the review exists, delete it
        if (existingReviewOptional.isPresent()) {
            Review existingReview = existingReviewOptional.get();
            String isbn = existingReview.getIsbn();

            // delete the review
            reviewRepository.deleteById(new ObjectId(id));

            // remove the reviewID from the associated book's reviewIds list
            mongoTemplate.update(Book.class).matching(Criteria.where("isbn").is(isbn))
                    .apply(new Update().pull("reviewIds", new ObjectId(id))).first();

            return existingReview;
        } else {
            return null;
        }
    }

}
