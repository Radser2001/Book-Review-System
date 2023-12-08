package com.radser2001.books.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.radser2001.books.models.Review;
import com.radser2001.books.services.ReviewService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // create review
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Review>(reviewService.createReview(payload.get("reviewBody"), payload.get("isbn")),
                HttpStatus.CREATED);
    }

    // update review
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable String id, @RequestBody Map<String, String> payload) {
        Review updatedReview = reviewService.updateReview(id, payload.get("reviewBody"),
                payload.get(payload.get("isbn")));

        if (updatedReview != null) {
            return new ResponseEntity<Review>(updatedReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
        }
    }

    // delete review
    @DeleteMapping("/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable String id) {
        Review deletedReview = reviewService.deleteReview(id);

        if (deletedReview != null) {
            return new ResponseEntity<Review>(deletedReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
        }
    }
}
