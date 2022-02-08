package com.example.cinema.model.service;

import com.example.cinema.model.connectionpool.ConnectionPool;
import com.example.cinema.model.dao.*;
import com.example.cinema.model.dao.exceptions.DaoException;
import com.example.cinema.model.entity.Movie;
import com.example.cinema.model.entity.Seance;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * The class contains all the logic for working with movies and seances.
 * It handles data, that is given from DAO, and then sends the result to certain command or another service.
 *
 */
public class MovieSeanceService {
    private static Logger log = Logger.getLogger(UserDao.class);
    private final SeanceDao seanceDao = SeanceDao.getInstance();
    private final MovieDao movieDao = MovieDao.getInstance();
    private static MovieSeanceService movieSeanceService;

    public static synchronized MovieSeanceService getInstance() {
        if (movieSeanceService == null) {
            movieSeanceService = new MovieSeanceService();
        }
        return movieSeanceService;
    }

    private MovieSeanceService() { }

    public List<Movie> getAllMovies() {
        return movieDao.getAllMovies();
    }

    public List<Seance> getAllSeances() {
        return seanceDao.getAllSeances();
    }

    public Movie getMovieById(int movieId) {
        return movieDao.getMovieById(movieId);
    }

    public List<Seance> getUniqueFutureSeancesPaginated(int startId, int total) {
        return seanceDao.getUniqueFutureSeancesPaginated(startId, total);
    }
    public List<Seance> getFutureSeancesPaginated(int startId, int total) {
        return seanceDao.getFutureSeancesPaginated(startId,total);
    }

    public int getUniqueSeancesQuantity() {
        return seanceDao.getUniqueSeancesQuantity();
    }

//    public List<Movie> getUniqueMovies(List<Seance> seanceList) {
//        List<Movie> movieList = new LinkedList<>();
//        for (Seance seance : seanceList) {
//            if (!movieList.contains(seance.getMovie())) movieList.add(seance.getMovie());
//        }
//        return movieList;
//    }

    public List<Seance> getCertainMovieSeances(Movie movie) {
        return seanceDao.getCertainMovieSeances(movie);
    }

    public List<Seance> getOnlyFutureSeances(List<Seance> seancesList) {
        List<Seance> futureSeanceslist = new LinkedList<>();
        for (Seance seance : seancesList) {
            if (LocalDateTime.now().isBefore(seance.getStartDate())) futureSeanceslist.add(seance);
        }
        return futureSeanceslist;
    }
    public List<LocalDate> getDatesOfSeances(List<Seance> seancesList) {
        List<LocalDate> dates = new LinkedList<>();
        for (Seance seance : seancesList) {
            LocalDate date = seance.getStartDate().toLocalDate();
            if (!dates.contains(date)) dates.add(date);
        }
        return dates;
    }

    public Map<LocalDate,List<Seance>> groupSeancesByDate(List<LocalDate> datesList, List<Seance> seanceList) {
        Map<LocalDate,List<Seance>> map = new HashMap<>();
        for(LocalDate date : datesList) {
            List<Seance> seances = new LinkedList<>();
            for(Seance seance : seanceList) {
                if (seance.getStartDate().getYear() == date.getYear() &&
                        seance.getStartDate().getMonth() == date.getMonth() &&
                        seance.getStartDate().getDayOfMonth() == date.getDayOfMonth() ){
                    seances.add(seance);
                }
            }
            map.put(date,seances);
        }
        return map;
    }

    public Seance getSeanceById(int id) {
        return seanceDao.getSeanceById(id);
    }


    public int getFutureSeancesQuantity() {
        return seanceDao.getFutureSeancesQuantity();
    }

    public boolean addMovie(String title, String director, int year, int genreId, int duration, int age, String imagePath) {
        return movieDao.addMovie(title, director, year, genreId, duration, age, imagePath);
    }

    public boolean deleteMovie(int movieId) {
        return movieDao.deleteMovie(movieId);
    }

    public void addSeance(int movieId, long seconds) {
        seanceDao.addSeance(movieId, seconds);
    }

    public void deleteSeance(int seanceId) {
        seanceDao.deleteSeance(seanceId);
    }
}
