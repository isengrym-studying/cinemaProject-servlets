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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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

    public List<Seance> getAllSeances() {
        LinkedList<Seance> list = new LinkedList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.UserQuery.GET_ALL_SEANCES);
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
            log.error(e.getMessage());
            throw new DaoException("Couldn't get films from DB", e);
        }
        return list;
    }

}
