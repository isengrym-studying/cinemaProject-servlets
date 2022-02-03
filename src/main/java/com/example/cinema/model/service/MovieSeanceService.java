package com.example.cinema.model.service;

import com.example.cinema.model.dao.MovieDao;
import com.example.cinema.model.dao.SeanceDao;
import com.example.cinema.model.entity.Movie;
import com.example.cinema.model.entity.Seance;

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
    private static MovieSeanceService movieSeanceService;

    public static synchronized MovieSeanceService getInstance() {
        if (movieSeanceService == null) {
            movieSeanceService = new MovieSeanceService();
        }
        return movieSeanceService;
    }

    private MovieSeanceService() { }

    public List<Movie> getAllMovies() {
        MovieDao movieDao = MovieDao.getInstance();
        return movieDao.getAllMovies();
    }

    public List<Seance> getAllSeances() {
        SeanceDao seanceDao = SeanceDao.getInstance();
        return seanceDao.getAllSeances();
    }

    public Movie getMovieById(int movieId) {
        MovieDao movieDao = MovieDao.getInstance();
        return movieDao.getMovieById(movieId);
    }

    public List<Movie> getUniqueMovies(List<Seance> seanceList) {
        List<Movie> movieList = new LinkedList<>();
        for (Seance seance : seanceList) {
            if (!movieList.contains(seance.getMovie())) movieList.add(seance.getMovie());
        }
        return movieList;
    }

    public List<Seance> getCertainMovieSeances(Movie movie) {
        SeanceDao seanceDao = SeanceDao.getInstance();
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

}
