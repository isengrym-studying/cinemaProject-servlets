package com.example.cinema.model.dao;

import com.example.cinema.model.connectionpool.ConnectionPool;
import com.example.cinema.model.dao.exceptions.DaoException;
import com.example.cinema.model.entity.Movie;
import com.example.cinema.model.entity.Seance;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Data access object for Seance-entity. (Singleton pattern is implemented)
 *
 */

public class SeanceDao {
    private static Logger log = Logger.getLogger(SeanceDao.class);
    public static SeanceDao instance = null;

    public static synchronized SeanceDao getInstance() {
        if (instance == null) {
            instance = new SeanceDao();
        }
        return instance;
    }

    private SeanceDao() {}


    /**
     * Method is being used for getting all seances (all fields from DB `seances` TABLE) as list.
     * Returns empty list if nothing was found
     * @return Returns List<Seance> (if there are fields in `seances` TABLE).
     * Returns empty list (if there are no fields in `seances` TABLE).
     * @exception DaoException catches SQLException and throws custom DAO-layer exception.
     */
    public List<Seance> getAllSeances() {
        List<Seance> list = new LinkedList<>();
        log.info("Getting all `seance` objects from DB");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.MoviesSeancesQuery.GET_ALL_SEANCES)) {

            try (ResultSet resSet = statement.executeQuery()) {
                while(resSet.next()) {
                    Movie movie = new Movie();
                    movie.setId(resSet.getInt("movie_id"));
                    movie.setTitle(resSet.getString("title"));
                    movie.setDirector(resSet.getString("director"));
                    movie.setProductionYear(resSet.getInt("production_year"));
                    movie.setDuration(Duration.ofMinutes(resSet.getLong("duration_minutes")));
                    movie.setImagePath(resSet.getString("image_path"));

                    Seance seance = new Seance();
                    int seanceId = resSet.getInt("seance_id");
                    int year = resSet.getInt("year");
                    int month = resSet.getInt("month");
                    int day = resSet.getInt("day");
                    int hour = resSet.getInt("hour");
                    int minute = resSet.getInt("minute");
                    seance.setId(seanceId);
                    seance.setStartDate(LocalDateTime.of(year,month,day,hour,minute));
                    seance.setMovie(movie);

                    list.add(seance);
                }
            }

        } catch (SQLException e) {
            log.error("SQLException in SeanceDao.getAllSeances() " + e.getMessage());
            throw new DaoException("Couldn't get films from DB", e);
        }
        return list;
    }


    /**
     * Method is being used for getting all seances (fields from DB `seances` TABLE) for certain movie, which is given as parameter
     * Returns empty list if nothing was found
     * @return Returns List<Seance> (if there are seances for given movie).
     * Returns empty list (if there are no seances for given movie).
     * @exception DaoException catches SQLException and throws custom DAO-layer exception.
     */
    public List<Seance> getCertainMovieSeances(Movie movie) {
        List<Seance> list = new LinkedList<>();
        log.info("Getting `seance` objects from DB for certain `movie` object");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.MoviesSeancesQuery.GET_SEANCES_FOR_MOVIE)) {
            statement.setInt(1,movie.getId());
            try (ResultSet resSet = statement.executeQuery()) {
                while(resSet.next()) {

                    Seance seance = new Seance();
                    int seanceId = resSet.getInt("seance_id");
                    int year = resSet.getInt("year");
                    int month = resSet.getInt("month");
                    int day = resSet.getInt("day");
                    int hour = resSet.getInt("hour");
                    int minute = resSet.getInt("minute");
                    seance.setId(seanceId);
                    seance.setStartDate(LocalDateTime.of(year,month,day,hour,minute));
                    seance.setMovie(movie);

                    list.add(seance);
                }
            }

        } catch (SQLException e) {
            log.error("SQLException in SeanceDao.getCertainMovieSeances() " + e.getMessage());
            throw new DaoException("Couldn't get films from DB", e);
        }
        return list;
    }

}
