package com.example.cinema.model.dao;

import com.example.cinema.model.connectionpool.ConnectionPool;
import com.example.cinema.model.dao.exceptions.DaoException;
import com.example.cinema.model.entity.Movie;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

public class MovieDao {
    private static Logger log = Logger.getLogger(MovieDao.class);
    private static MovieDao movieDao;

    public static synchronized MovieDao getInstance() {
        if (movieDao == null) {
            movieDao = new MovieDao();
        }
        return movieDao;
    }

    private MovieDao() {
    }

    public List<Movie> getAllMovies() {
        LinkedList<Movie> list = new LinkedList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.UserQuery.GET_ALL_MOVIES);
             ) {

            try (ResultSet resSet = statement.executeQuery()) {
                while(resSet.next()) {
                    Movie movie = new Movie();
                    movie.setId(resSet.getInt("movie_id"));
                    movie.setTitle(resSet.getString("title"));
                    movie.setDirector(resSet.getString("director"));
                    movie.setProductionYear(resSet.getInt("production_year"));
                    movie.setDuration(Duration.ofMinutes(resSet.getLong("duration_minutes")));
                    movie.setImagePath(resSet.getString("image_path"));
                    list.add(movie);
                }
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DaoException("Couldn't get films from DB", e);
        }
        return list;
    }

    public Movie getMovieById(int id) {
        Movie movie = new Movie();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.UserQuery.GET_MOVIE_BY_ID)) {

            statement.setInt(1,id);

            try (ResultSet resSet = statement.executeQuery()) {
                if (resSet.next()) {
                    movie.setId(id);
                    movie.setTitle(resSet.getString("title"));
                    movie.setDirector(resSet.getString("director"));
                    movie.setProductionYear(resSet.getInt("production_year"));
                    movie.setGenre(resSet.getString("genre_name"));
                    movie.setAgeRestriction(resSet.getInt("age_restriction"));
                    movie.setDuration(Duration.ofMinutes(resSet.getInt("duration_minutes")));
                    movie.setImagePath(resSet.getString("image_path"));
            }

            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DaoException("Couldn't find film by given id (" + id +")", e);
        }
        return movie;
    }


}