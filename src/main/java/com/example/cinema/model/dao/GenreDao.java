package com.example.cinema.model.dao;

import com.example.cinema.model.connectionpool.ConnectionPool;
import com.example.cinema.model.dao.exceptions.DaoException;
import com.example.cinema.model.entity.Genre;
import com.example.cinema.model.entity.Movie;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

/**
 * Data access object for Genre-entity. (Singleton pattern is implemented)
 *
 */
public class GenreDao {
    private static Logger log = Logger.getLogger(GenreDao.class);
    private static GenreDao genreDao;

    public static synchronized GenreDao getInstance() {
        if (genreDao == null) {
            genreDao = new GenreDao();
        }
        return genreDao;
    }

    private GenreDao() {}

    public List<Genre> getAllGenres() {
        List<Genre> list = new LinkedList<>();
        log.info("Getting all `genre` objects from DB");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.GenresQuery.GET_ALL_GENRES)) {

            try (ResultSet resSet = statement.executeQuery()) {
                while(resSet.next()) {
                    Genre genre = new Genre();
                    genre.setId(resSet.getInt("genre_id"));
                    genre.setName(resSet.getString("genre_name"));

                    list.add(genre);
                }
                log.info("Successfully got all `genre` objects from DB");
            }

        } catch (SQLException e) {
            log.error("SQLException in GenreDao.getAllGenres() " + e.getMessage());
            throw new DaoException("Couldn't get genres from DB", e);
        }
        return list;
    }
}
