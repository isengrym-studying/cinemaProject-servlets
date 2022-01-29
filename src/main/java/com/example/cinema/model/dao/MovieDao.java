package com.example.cinema.model.dao;

import com.example.cinema.model.connectionpool.ConnectionPool;
import com.example.cinema.model.dao.exceptions.DaoException;
import com.example.cinema.model.entity.Movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

public class MovieDao {
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
        ResultSet resSet = null;
        LinkedList<Movie> list = new LinkedList<>();
        try (PreparedStatement statement = ConnectionPool
                .getInstance().getConnection().prepareStatement(SQLQuery.UserQuery.FIND_ALL_MOVIES)) {
            resSet = statement.executeQuery();
            while(resSet.next()) {
                Movie movie = new Movie();
                movie.setTitle(resSet.getString("title"));
                movie.setDirector(resSet.getString("director"));
                movie.setProductionYear(resSet.getInt("production_year"));
                movie.setDuration(Duration.ofMinutes(resSet.getLong("duration_minutes")));
                movie.setImagePath(resSet.getString("image_path"));
                list.add(movie);
            }
        } catch (SQLException e) {
            throw new DaoException("Couldn't get films from DB", e);
        }
        return list;
    }

}
