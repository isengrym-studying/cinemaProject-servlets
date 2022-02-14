package com.example.cinema.model.dao;

import com.example.cinema.model.dao.exceptions.connectionpool.ConnectionPool;
import com.example.cinema.model.dao.exceptions.DaoException;
import com.example.cinema.model.entity.Review;
import com.example.cinema.model.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;

public class ReviewDao {
    private static Logger log = Logger.getLogger(ReviewDao.class);
    public static ReviewDao instance = null;

    public static synchronized ReviewDao getInstance() {
        if (instance == null) {
            instance = new ReviewDao();
        }
        return instance;
    }

    private ReviewDao() {}

    public Review getReviewByUserId(int userId, int movieId) {
        log.info("Getting review for movie with("+ movieId +") from user with id ("+ userId +") from DB");
        Review review = new Review();
        User user = new User();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.ReviewsQuery.GET_REVIEW_BY_USER_AND_MOVIE)) {
            statement.setInt(1, userId);
            statement.setInt(2, movieId);
            try (ResultSet resSet = statement.executeQuery()) {
                if (resSet.next()) {
                    review.setId(resSet.getInt("review_id"));
                    user.setId(resSet.getInt("user_id"));
                    user.setName(resSet.getString("name"));
                    user.setSurname(resSet.getString("surname"));
                    review.setUser(user);
                    review.setMovieId(resSet.getInt("movie_id"));
                    review.setText(resSet.getString("review_text"));
                    long epoch = resSet.getInt("review_date");
                    review.setDate(LocalDateTime.ofEpochSecond(epoch, 0, ZoneOffset.UTC));

                    log.info("Successfully found review with id ("+review.getId()+") from DB");
                }
                else {
                    log.warn("No reviews were found for movie with("+ movieId +") from user with id ("+ userId +")");
                }
            }

        } catch (SQLException e) {
            log.error("SQLException in ReviewDao.getReviewByUserId " + e.getMessage());
            throw new DaoException("Couldn't get review by user and movie id`s", e);
        }
        return review;
    }

    public List<Review> getReviewsForMovie(int movieId, int startId, int total) {
        List<Review> list = new LinkedList<>();
        log.info("Getting " + total + " reviews with movie id ("+movieId+") from DB");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.ReviewsQuery.GET_REVIEWS_FOR_MOVIE_PAGINATED)) {
            statement.setInt(1, movieId);
            statement.setInt(2,startId);
            statement.setInt(3,total);

            try (ResultSet resSet = statement.executeQuery()) {
                while(resSet.next()) {
                    Review review = new Review();
                    User user = new User();
                    review.setId(resSet.getInt("review_id"));
                    user.setId(resSet.getInt("user_id"));
                    user.setName(resSet.getString("name"));
                    user.setSurname(resSet.getString("surname"));
                    review.setUser(user);
                    review.setMovieId(resSet.getInt("movie_id"));
                    review.setText(resSet.getString("review_text"));
                    long epoch = resSet.getInt("review_date");
                    review.setDate(LocalDateTime.ofEpochSecond(epoch, 0, ZoneOffset.UTC));
                    list.add(review);
                }
                log.info("Successfully got " + total + " reviews from DB");
            }

        } catch (SQLException e) {
            log.error("SQLException in ReviewDao.getReviewsForMovie() " + e.getMessage());
            throw new DaoException("Couldn't get reviews from DB (beginning from"+startId +" element," +
                    " "+total+" elements in total", e);
        }
        return list;
    }

    public int getQuantityOfReviews(int movieId) {
        log.info("Counting reviews with movie id ("+movieId+")");
        int numberOfReviews = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.ReviewsQuery.COUNT_REVIEWS_FOR_MOVIE)) {
            statement.setLong(1, movieId);
            try (ResultSet resSet = statement.executeQuery()) {
                if (resSet.next()) {
                    numberOfReviews = resSet.getInt("count");
                    log.info("Successfully got number of reviews for movie (" +numberOfReviews +" reviews)");
                }
            }

        } catch (SQLException e) {
            log.error("SQLException in ReviewDao.getQuantityOfReviews() " + e.getMessage());
            throw new DaoException("Couldn't get number of reviews", e);
        }
        return numberOfReviews;
    }

    public void addReview(Review review) {
        log.info("Adding review ("+ review.getId() + ") to DB" );

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.ReviewsQuery.ADD_REVIEW)) {
            statement.setInt(1, review.getUser().getId());
            statement.setInt(2, review.getMovieId());
            statement.setString(3, review.getText());
            statement.setLong(4,LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

            statement.executeUpdate();
            log.info("Adding review ("+ review.getId() + ") to DB was successfully finished");
        } catch (SQLException e) {
            log.error("SQLException in ReviewDao.addReview() " + e.getMessage());
            throw new DaoException("Couldn't add review to the DB", e);
        }
    }

    public void updateReview(Review review) {
        log.info("Update review ("+ review.getId() + ") to DB" );

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.ReviewsQuery.UPDATE_REVIEW)) {

            statement.setString(1, review.getText());
            statement.setLong(2, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
            statement.setInt(3, review.getUser().getId());
            statement.setInt(4, review.getMovieId());

            statement.executeUpdate();
            log.info("Update review ("+ review.getId() + ") to DB was successfully finished");
        } catch (SQLException e) {
            log.error("SQLException in ReviewDao.updateReview() " + e.getMessage());
            throw new DaoException("Couldn't update review to the DB", e);
        }
    }

    public void deleteReview(Review review) {
        log.info("Deleting review (" + review.getId() +")");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.ReviewsQuery.DELETE_REVIEW)) {

            statement.setInt(1, review.getUser().getId());
            statement.setInt(2, review.getMovieId());

            statement.execute();
            log.info("Deleting review ("+ review.getId() + ") from DB was successfully finished");
        } catch (SQLException e) {
            log.error("SQLException in UserDao.deleteReview() " + e.getMessage());
            throw new DaoException("Couldn't delete review", e);
        }
    }
}
