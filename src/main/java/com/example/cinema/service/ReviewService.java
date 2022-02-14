package com.example.cinema.service;

import com.example.cinema.model.dao.ReviewDao;
import com.example.cinema.model.entity.Review;
import com.example.cinema.service.validator.ReviewValidator;

import java.util.List;

public class ReviewService {
    private static ReviewService reviewService;
    private final ReviewDao reviewDao = ReviewDao.getInstance();

    public static synchronized ReviewService getInstance() {
        if (reviewService == null) {
            reviewService = new ReviewService();
        }
        return reviewService;
    }

    public Review getReviewByUserId(int userId, int movieId) {
        return reviewDao.getReviewByUserId(userId, movieId);
    }

    public List<Review> getReviewsForMovie(int movieId, int startId, int total) { return reviewDao.getReviewsForMovie(movieId, startId, total); }

    public int getReviewsQuantity(int movieId) {
        return reviewDao.getQuantityOfReviews(movieId);
    }

    public boolean reviewIsValid(Review givenReview) {
        return ReviewValidator.validate(givenReview.getText());
    }

    public void addReview(Review review) {
        reviewDao.addReview(review);
    }

    public void updateReview(Review review) {
        reviewDao.updateReview(review);
    }

    public void deleteReview(Review review) {
        reviewDao.deleteReview(review);
    }
}
