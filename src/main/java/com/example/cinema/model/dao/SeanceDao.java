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


    public Seance getSeanceById(int id) {
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
                    seance.setMovie(movie);
                }
            }

        } catch (SQLException e) {
            log.error("SQLException in SeanceDao.getSeanceById() " + e.getMessage());
            throw new DaoException("Couldn't get seance by id", e);
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
            statement.setInt(1,movie.getId());
            try (ResultSet resSet = statement.executeQuery()) {
                while(resSet.next()) {

                    Seance seance = new Seance();
                    long epoch = resSet.getInt("startDateSeconds");

                    seance.setId(resSet.getInt("seance_id"));
                    seance.setStartDate(LocalDateTime.ofEpochSecond(epoch,0, ZoneOffset.UTC));
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

    public List<Seance> getUniqueFutureSeancesPaginated(int startId, int total) {
        List<Seance> list = new LinkedList<>();
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

                    Seance seance = new Seance();
                    long epoch = resSet.getInt("startDateSeconds");

                    seance.setId(resSet.getInt("seance_id"));
                    seance.setStartDate(LocalDateTime.ofEpochSecond(epoch,0, ZoneOffset.UTC));
                    seance.setMovie(movie);

                    list.add(seance);
                }
                log.info("Successfully got " + startId + " unique future `seance` objects from DB");
            }

        } catch (SQLException e) {
            log.error("SQLException in SeanceDao.getUniqueFutureSeancesPaginated() " + e.getMessage());
            throw new DaoException("Couldn't get unique future seances from DB", e);
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

    public List<Seance> getFutureSeancesPaginated(int startId, int total) {
        List<Seance> list = new LinkedList<>();
        log.info("Getting " + startId + " unique future `seance` objects from DB");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.MoviesSeancesQuery.GET_FUTURE_SEANCES_PAGINATED)) {
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

                    Seance seance = new Seance();
                    long epoch = resSet.getInt("startDateSeconds");

                    seance.setId(resSet.getInt("seance_id"));
                    seance.setStartDate(LocalDateTime.ofEpochSecond(epoch,0, ZoneOffset.UTC));
                    seance.setMovie(movie);

                    list.add(seance);
                }
                log.info("Successfully got " + startId + " unique future `seance` objects from DB");
            }

        } catch (SQLException e) {
            log.error("SQLException in SeanceDao.getUniqueFutureSeancesPaginated() " + e.getMessage());
            throw new DaoException("Couldn't get unique future seances from DB", e);
        }
        return list;
    }

    public int getFutureSeancesQuantity() {
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
            log.error("SQLException in SeanceDao.getFutureSeancesQuantity() " + e.getMessage());
            throw new DaoException("Couldn't get number of future seances", e);
        }
        return numberOfSeances;
    }
}
