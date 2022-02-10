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
import java.time.ZoneOffset;
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

    public Seance getSeanceById(int id) {
        log.info("Getting`seance` object by id("+ id +") from DB");
        Seance seance = new Seance();
        Movie movie = new Movie();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.MoviesSeancesQuery.GET_SEANCE_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resSet = statement.executeQuery()) {
                while(resSet.next()) {

                    movie.setId(resSet.getInt("movie_id"));
                    movie.setTitle(resSet.getString("title"));
                    movie.setDirector(resSet.getString("director"));
                    movie.setProductionYear(resSet.getInt("production_year"));
                    movie.setGenre(resSet.getString("genre"));
                    movie.setAgeRestriction(resSet.getInt("age_restriction"));
                    movie.setDuration(Duration.ofMinutes(resSet.getInt("duration_minutes")));
                    movie.setImagePath(resSet.getString("image_path"));

                    long epoch = resSet.getInt("startDateSeconds");

                    seance.setId(resSet.getInt("seance_id"));
                    seance.setStartDate(LocalDateTime.ofEpochSecond(epoch,0, ZoneOffset.UTC));
                    seance.setTicketPrice(resSet.getInt("ticket_price"));
                    seance.setFreePlaces(resSet.getInt("free_places"));
                    seance.setMovie(movie);
                }
                log.info("Successfully Seance object by id ("+id+") from DB");
            }

        } catch (SQLException e) {
            log.error("SQLException in SeanceDao.getSeanceById() " + e.getMessage());
            throw new DaoException("Couldn't get seances by id ("+id+")", e);
        }
        return seance;
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
            statement.setLong(1, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
            statement.setInt(2,movie.getId());
            try (ResultSet resSet = statement.executeQuery()) {
                while(resSet.next()) {

                    Seance seance = new Seance();
                    long epoch = resSet.getInt("startDateSeconds");

                    seance.setId(resSet.getInt("seance_id"));
                    seance.setStartDate(LocalDateTime.ofEpochSecond(epoch,0, ZoneOffset.UTC));
                    seance.setTicketPrice(resSet.getInt("ticket_price"));
                    seance.setFreePlaces(resSet.getInt("free_places"));
                    seance.setMovie(movie);

                    list.add(seance);
                }
                log.info("Successfully got list of Seance objects for movie with id "+movie.getId()+" from DB");
            }

        } catch (SQLException e) {
            log.error("SQLException in SeanceDao.getCertainMovieSeances() " + e.getMessage());
            throw new DaoException("Couldn't get movies for seance with id ("+ movie.getId() +") from DB", e);
        }
        return list;
    }

    public List<Movie> getUniqueSeancesPaginated(int startId, int total) {
        List<Movie> list = new LinkedList<>();
        log.info("Getting " + startId + " unique future `seance` objects from DB");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.MoviesSeancesQuery.GET_UNIQUE_FUTURE_SEANCES_PAGINATED)) {
            statement.setLong(1, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
            statement.setInt(2,startId);
            statement.setInt(3,total);

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
                log.info("Successfully got " + startId + " unique `seance` objects from DB");
            }

        } catch (SQLException e) {
            log.error("SQLException in SeanceDao.getUniqueFutureSeancesPaginated() " + e.getMessage());
            throw new DaoException("Couldn't get unique future seances from DB (beginning from"+startId +" element," +
                    " "+total+" elements in total", e);
        }
        return list;
    }

    public int getUniqueSeancesQuantity() {
        log.info("Counting seances with unique movie id`s");
        int numberOfSeances = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.MoviesSeancesQuery.COUNT_UNIQUE_FUTURE_SEANCES)) {
            statement.setLong(1,LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
            try (ResultSet resSet = statement.executeQuery()) {
                if (resSet.next()) {
                    numberOfSeances = resSet.getInt("count");
                    log.info("Successfully got number of seances with unique movies (" +numberOfSeances +")");
                }
            }

        } catch (SQLException e) {
            log.error("SQLException in SeanceDao.getUniqueSeancesQuantity() " + e.getMessage());
            throw new DaoException("Couldn't get number of unique seances", e);
        }
        return numberOfSeances;
    }

    public int getSeancesQuantity() {
        log.info("Counting future seances");
        int numberOfSeances = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.MoviesSeancesQuery.COUNT_FUTURE_SEANCES)) {
            statement.setLong(1,LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
            try (ResultSet resSet = statement.executeQuery()) {
                if (resSet.next()) {
                    numberOfSeances = resSet.getInt("count");
                    log.info("Successfully got number of future seances (" +numberOfSeances +")");
                }
            }

        } catch (SQLException e) {
            log.error("SQLException in SeanceDao.getSeancesQuantity() " + e.getMessage());
            throw new DaoException("Couldn't get number of future seances", e);
        }
        return numberOfSeances;
    }

    public void addSeance(int movieId, long seconds, int price) {
        log.info("Adding new seance to DB");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.MoviesSeancesQuery.ADD_SEANCE)) {

            statement.setInt(1, movieId);
            statement.setLong(2, seconds);
            statement.setInt(3, price);

            statement.execute();
            log.info("New Seance was successfully added to DB");
        } catch (SQLException e) {
            log.error("SQLException in SeanceDao.addSeance() " + e.getMessage());
            throw new DaoException("Couldn't add seance to DB", e);
        }
    }

    public void deleteSeance(int seanceId) {
        log.info("Deleting seance with id " + seanceId);

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.MoviesSeancesQuery.DELETE_SEANCE)) {

            statement.setInt(1, seanceId);

            statement.execute();
            log.info("Deleting seance ("+ seanceId + ") from DB was successfully finished");
        } catch (SQLException e) {
            log.error("SQLException in SeanceDao.deleteSeance() " + e.getMessage());
            throw new DaoException("Couldn't delete seance", e);
        }
    }

    public List<Seance> getSeancesByDatePaginated(String order, int startId, int totalOnPage) {
        log.info("Getting " + startId + " `seance` objects from DB ordered by date");
        String query = String.format(SQLQuery.MoviesSeancesQuery.GET_SEANCES_BY_DATE_PAGINATED,order);

        return getSeancesPaginated(query, startId, totalOnPage);
    }

    public List<Seance> getSeancesBySeatsPaginated(String order, int startId, int totalOnPage) {
        log.info("Getting " + startId + " `seance` objects from DB ordered by date");
        String query = String.format(SQLQuery.MoviesSeancesQuery.GET_SEANCES_BY_FREE_PLACES_PAGINATED, order);

        return getSeancesPaginated(query, startId, totalOnPage);
    }

    public List<Seance> getSeancesForMovieByDatePaginated(int movieId, String order, int startId, int totalOnPage) {
        log.info("Getting " + startId + " `seance` objects from DB ordered by date for movie with id (" +movieId +")");
        String query = String.format(SQLQuery.MoviesSeancesQuery.GET_SEANCES_FOR_MOVIE_BY_DATE_PAGINATED,order);

        return getSeancesForMoviePaginated(query, movieId, startId, totalOnPage);
    }

    public List<Seance> getSeancesForMovieBySeatsPaginated(int movieId, String order, int startId, int totalOnPage) {
        log.info("Getting " + startId + " `seance` objects from DB ordered by date for movie with id (" +movieId +")");
        String query = String.format(SQLQuery.MoviesSeancesQuery.GET_SEANCES_FOR_MOVIE_BY_FREE_PLACES_PAGINATED,order);
        return getSeancesForMoviePaginated(query, movieId, startId, totalOnPage);
    }

    public int getSeancesQuantityForMovie(int movieId) {
        log.info("Counting seances for movie with id ("+movieId+")");
        int numberOfSeances = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.MoviesSeancesQuery.COUNT_SEANCES_FOR_MOVIE)) {
            statement.setLong(1,LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
            statement.setLong(2,movieId);
            try (ResultSet resSet = statement.executeQuery()) {
                if (resSet.next()) {
                    numberOfSeances = resSet.getInt("count");
                    log.info("Successfully got number of seances (" +numberOfSeances +") for movie");
                }
            }

        } catch (SQLException e) {
            log.error("SQLException in SeanceDao.getSeancesQuantityForMovie() " + e.getMessage());
            throw new DaoException("Couldn't get number of seances for movie with id ("+movieId+")", e);
        }
        return numberOfSeances;
    }

    private List<Seance> getSeancesPaginated(String query, int startId, int totalOnPage) {
        List<Seance> list = new LinkedList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
            statement.setInt(2,startId);
            statement.setInt(3,totalOnPage);

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
                    long epoch = resSet.getInt("startDateSeconds");

                    seance.setId(resSet.getInt("seance_id"));
                    seance.setStartDate(LocalDateTime.ofEpochSecond(epoch,0, ZoneOffset.UTC));
                    seance.setTicketPrice(resSet.getInt("ticket_price"));
                    seance.setFreePlaces(resSet.getInt("free_places"));
                    seance.setMovie(movie);

                    list.add(seance);
                }
                log.info("Successfully got " + startId + " unique future `seance` objects from DB");
            }

        } catch (SQLException e) {
            log.error("SQLException in SeanceDao.getSeancesByDatePaginated() " + e.getMessage());
            throw new DaoException("Couldn't get seances from DB ordered by date", e);
        }
        return list;
    }

    private List<Seance> getSeancesForMoviePaginated(String query, int movieId, int startId, int totalOnPage) {
        List<Seance> list = new LinkedList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
            statement.setInt(2, movieId);
            statement.setInt(3,startId);
            statement.setInt(4,totalOnPage);

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
                    long epoch = resSet.getInt("startDateSeconds");

                    seance.setId(resSet.getInt("seance_id"));
                    seance.setStartDate(LocalDateTime.ofEpochSecond(epoch,0, ZoneOffset.UTC));
                    seance.setTicketPrice(resSet.getInt("ticket_price"));
                    seance.setFreePlaces(resSet.getInt("free_places"));
                    seance.setMovie(movie);

                    list.add(seance);
                }
                log.info("Successfully got " + startId + " `seance` objects from DB ordered by date for movie with id (" +movieId +")");
            }

        } catch (SQLException e) {
            log.error("SQLException in SeanceDao.getSeancesForMovieByDatePaginated() " + e.getMessage());
            throw new DaoException("Couldn't get seances from DB ordered by date for movie", e);
        }
        return list;
    }

}
