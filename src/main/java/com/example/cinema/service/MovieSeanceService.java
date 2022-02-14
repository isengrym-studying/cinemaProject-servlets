package com.example.cinema.service;

import com.example.cinema.model.dao.*;
import com.example.cinema.model.entity.Movie;
import com.example.cinema.model.entity.Seance;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * The class contains all the logic for working with movies and seances.
 * This layer is located between DAO and Controller layers
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

    /**
     * Method for getting list of all movies. Method just resends to the relevant DAO method and
     * contains no additional logic
     * @return Returns filled List<Movie> (If movies were found).
     * Returns empty List<Movie> (If no movies were found).
     *
     */
    public List<Movie> getAllMovies() {
        return movieDao.getAllMovies();
    }

    /**
     * Method for getting quantity of all movies. Method just resends to the relevant DAO method and
     * contains no additional logic
     * @return Returns int (quantity of movies)
     *
     */
    public int getMoviesQuantity() { return movieDao.getMovieQuantity(); }


    /**
     * Method for getting a movie by id given as parameter. Method just resends to the relevant DAO method and
     * contains no additional logic
     * @return Returns filled Movie object (If movie was found).
     * Returns empty Movie object (If no movie was found).
     * @param movieId id of the movie, that is going to be used for finding movie in DB
     *
     */
    public Movie getMovieById(int movieId) {
        return movieDao.getMovieById(movieId);
    }

    /**
     * Method for getting list of all limited (paginated) movies. Method just resends to the relevant DAO method and
     * contains no additional logic
     * @param startId index from the record from which the selection of tickets will start
     * @param totalOnPage quantity of movies, that are going to be returned
     * @return Returns filled List<Movie>(If movies were found).
     * Returns empty List<Movie>(If no movies were found).
     *
     */
    public List<Movie> getAllMoviesPaginated(int startId, int totalOnPage) {
        return movieDao.getAllMoviesPaginated(startId,totalOnPage);
    }

    /**
     * Method for getting list of limited (paginated) movies, that have seances. Method just resends to the relevant DAO method and
     * contains no additional logic
     * @param startId index from the record from which the selection of items will start
     * @param total quantity of items, that are going to be returned
     * @return Returns filled List<Movie>(If movies were found).
     * Returns empty List<Movie>(If no movies were found).
     *
     */
    public List<Movie> getMoviesWithSeancesPaginated(int startId, int total) {
        return seanceDao.getUniqueSeancesPaginated(startId, total);
    }

    /**
     * Method for getting quantity of all seances with unique movie id`s (basically,
     * movies, that have seances). Method just resends to the relevant DAO method and
     * contains no additional logic
     * @return Returns int (quantity of seances)
     *
     */
    public int getUniqueSeancesQuantity() {
        return seanceDao.getUniqueSeancesQuantity();
    }

    /**
     * Method for getting list of seances for definite movie. Method just resends to
     * the relevant DAO method and contains no additional logic.
     * @param movie Movie object, for which the seances are being searched
     * @return Returns filled List<Seance>(If seances were found).
     * Returns empty List<Seance>(If no seances were found).
     *
     */
    public List<Seance> getCertainMovieSeances(Movie movie) {
        return seanceDao.getCertainMovieSeances(movie);
    }


    /**
     * Method for getting list of dates, when there are seances.
     * @param seancesList simple list of seances. May contain no future seances.
     * @return List<Seance> (list with dates)
     *
     */
    public List<LocalDate> getDatesOfSeances(List<Seance> seancesList) {
        List<LocalDate> dates = new LinkedList<>();
        for (Seance seance : seancesList) {
            LocalDate date = seance.getStartDate().toLocalDate();
            if (!dates.contains(date)) dates.add(date);
        }
        return dates;
    }


    /**
     * Method that returns a map, where the key is date, and the value is list of seances, that are
     * going to take place in that date.
     * @param datesList simple list of dates
     * @param seanceList simple list of seances
     * @return Returns Map<LocalDate,List<Seance>>
     *
     */
    public Map<LocalDate,List<Seance>> groupSeancesByDate(List<LocalDate> datesList, List<Seance> seanceList) {
        Map<LocalDate,List<Seance>> map = new LinkedHashMap<>();

        for(LocalDate date : datesList) {
            List<Seance> seances = seanceList.stream()
                    .filter(seance -> seance.getStartDate().getYear() == date.getYear())
                    .filter(seance -> seance.getStartDate().getMonth() == date.getMonth())
                    .filter(seance -> seance.getStartDate().getDayOfMonth() == date.getDayOfMonth())
                    .collect(Collectors.toList());
            map.put(date,seances);
        }
        return map;
    }

    /**
     * Method that returns the Seance object by its id, that is given as a parameter
     * @param id id of Seance object
     * @return Returns Seance object (filled or empty)
     *
     */
    public Seance getSeanceById(int id) {
        return seanceDao.getSeanceById(id);
    }

    /**
     * Method that is responsible for adding movie to DB. Method just resends to the relevant DAO method and
     * contains no additional logic
     * @param movie Movie object, that should be added to DB
     * @return Returns boolean (depending on the result)
     *
     */
    public void addMovie(Movie movie) {
        movieDao.addMovie(movie);
    }

    /**
     * Method that is responsible for deleting movie from DB. Method just resends to the relevant DAO method and
     * contains no additional logic
     * @param movieId id of Movie record, that should be deleted from the DB
     * @return Returns boolean (depending on the result)
     *
     */
    public boolean deleteMovie(int movieId) {
        return movieDao.deleteMovie(movieId);
    }

    /**
     * Method that is responsible for adding seance to DB. Method just resends to the relevant DAO method and
     * contains no additional logic
     * @param seanceId id of Seance object, that should be added to the DB
     * @return Returns void
     *
     */
    public void addSeance(int seanceId, long seconds, int price) {
        seanceDao.addSeance(seanceId, seconds, price);
    }

    /**
     * Method that is responsible for deleting seance from DB. Method just resends to the relevant DAO method and
     * contains no additional logic
     * @param seanceId id of Seance record, that should be deleted from the DB
     * @return Returns void
     *
     */
    public void deleteSeance(int seanceId) {
        seanceDao.deleteSeance(seanceId);
    }

    /**
     * Method that is responsible for defining, which DAO method should be called (depends on movieId
     * and sorting value) for getting list of Seance objects. If movieId = -1, it means that seances should be not found for any specific movie, but all.
     * @param movieId id of Movie record, for which the seances are going to be searched (if movieId = -1, than for all movies)
     * @param sorting String contains way of sorting the seances.
     * @return List<Seance> received in one of the options.
     *
     */
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

    /**
     * Method that is responsible for defining, which DAO method should be called (depends on movieId)
     * for getting quantity of Seance objects. If movieId = -1, it means that seances
     * should be not found for any specific movie, but all.
     * @param movieId id of Movie record, for which the seances are going to be searched(if movieId = -1, than for all movies)
     * @return int - quantity of Seance objects, received in one of the options.
     *
     */
    public int getSeancesQuantityByParameters(int movieId) {
        if (movieId == -1) return seanceDao.getSeancesQuantity();
        else return seanceDao.getSeancesQuantityForMovie(movieId);

    }


}
