package com.example.cinema.model.service;

import com.example.cinema.model.dao.*;
import com.example.cinema.model.entity.Movie;
import com.example.cinema.model.entity.Seance;
import org.apache.log4j.Logger;

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

    public int getMoviesQuantity() { return movieDao.getMovieQuantity(); }

    public List<Seance> getAllSeances() {
        return seanceDao.getAllSeances();
    }

    public Movie getMovieById(int movieId) {
        return movieDao.getMovieById(movieId);
    }

    public List<Movie> getAllMoviesPaginated(int startId, int totalOnPage) {
        return movieDao.getAllMoviesPaginated(startId,totalOnPage);
    }

    public List<Movie> getUniqueFutureSeancesPaginated(int startId, int total) {
        return seanceDao.getUniqueFutureSeancesPaginated(startId, total);
    }


    public List<Seance> getFutureSeancesPaginated(int startId, int total) {
        return seanceDao.getFutureSeancesPaginated(startId,total);
    }

    public int getUniqueSeancesQuantity() {
        return seanceDao.getUniqueSeancesQuantity();
    }

//    public List<Movie> getUniqueMoviesWithSeances(List<Seance> seanceList) {
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
        return seanceDao.getSeancesQuantity();
    }

    public boolean addMovie(Movie movie) {
        return movieDao.addMovie(movie);
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

    public List<Seance> getSeancesByParametersPaginated(int movieId, String sorting, String order, int i, int totalOnPage) {
        final String sortByDate = "date";
        final String sortBySeats = "freeSeats";

        if (movieId == -1) {
            if (sorting.equals(sortByDate)) return seanceDao.getSeancesByDatePaginated(order, i, totalOnPage);
            else if (sorting.equals(sortBySeats)) return seanceDao.getSeancesBySeatsPaginated(order, i, totalOnPage);
            else return seanceDao.getSeancesByDatePaginated(order, i, totalOnPage);
        }
        else {
            if (sorting.equals(sortByDate)) return seanceDao.getSeancesForMovieByDatePaginated(movieId, order, i, totalOnPage);
            else if (sorting.equals(sortBySeats)) return seanceDao.getSeancesForMovieBySeatsPaginated(movieId, order, i, totalOnPage);
            else return seanceDao.getSeancesForMovieByDatePaginated(movieId, order, i, totalOnPage);

        }
    }

    public int getSeancesQuantityByParameters(int movieId) {
        if (movieId == -1) return seanceDao.getSeancesQuantity();
        else return seanceDao.getSeancesQuantityForMovie(movieId);

    }


}
