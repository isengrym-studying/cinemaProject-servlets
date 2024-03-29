package com.example.cinema.model.dao;

import com.example.cinema.model.dao.exceptions.connectionpool.ConnectionPool;
import com.example.cinema.model.dao.exceptions.DaoException;
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
 * Data access object for Movie-entity. (Singleton pattern is implemented)
 *
 */
public class MovieDao {
    private static Logger log = Logger.getLogger(MovieDao.class);
    private static MovieDao movieDao;

    public static synchronized MovieDao getInstance() {
        if (movieDao == null) {
            movieDao = new MovieDao();
        }
        return movieDao;
    }

    private MovieDao() {
    }

    /**
     * Method is being used for getting all movies (all fields from DB `movies` TABLE) as list.
     * Returns empty list if nothing was found
     * @return Returns List<Movie> (if there are fields in `movies` TABLE). Returns empty list (if there are no fields in `movies` TABLE).
     * @exception DaoException catches SQLException and throws custom DAO-layer exception.
     */
    public List<Movie> getAllMovies() {
        LinkedList<Movie> list = new LinkedList<>();
        log.info("Getting all `movie` objects from DB");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.MoviesSeancesQuery.GET_ALL_MOVIES)) {

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
                log.info("Successfully got all movies from DB");
            }

        } catch (SQLException e) {
            log.error("SQLException in MovieDao.getAllMovies() " + e.getMessage());
            throw new DaoException("Couldn't get films from DB", e);
        }
        return list;
    }

    public List<Movie> getAllMoviesPaginated(int startId, int totalOnPage) {
        LinkedList<Movie> list = new LinkedList<>();
        log.info("Getting " + totalOnPage + " `movie` objects from DB");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.MoviesSeancesQuery.GET_ALL_MOVIES_PAGINATED)) {

            statement.setInt(1,startId);
            statement.setInt(2,totalOnPage);
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
                log.info("Successfully got all movies paginated (beginning from "+startId+" element, "+totalOnPage +" elements in total) from DB");
            }

        } catch (SQLException e) {
            log.error("SQLException in MovieDao.getAllMoviesPaginated() " + e.getMessage());
            throw new DaoException("Failed at getting all movies paginated (beginning from "+startId+" element, "+totalOnPage +" elements in total) from DB");
        }
        return list;
    }


    /**
     * Method is being used for getting movie (field from DB `movies` TABLE) by given id.
     * Returns empty Movie-object if nothing was found
     * @return Returns Movie (if there are fields with given id in `movies` TABLE).
     * Returns empty Movie (if there are no fields with given id in `movies` TABLE).
     * @exception DaoException catches SQLException and throws custom DAO-layer exception.
     */
    public Movie getMovieById(int id) {
        Movie movie = new Movie();
        log.info("Getting all `movie` objects from DB by `id` = "+ id +" attribute");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.MoviesSeancesQuery.GET_MOVIE_BY_ID)) {

            statement.setInt(1,id);

            try (ResultSet resSet = statement.executeQuery()) {
                if (resSet.next()) {
                    log.info("Found at least one `movie` by given `id`= " + id +" attribute");
                    movie.setId(id);
                    movie.setTitle(resSet.getString("title"));
                    movie.setDirector(resSet.getString("director"));
                    movie.setProductionYear(resSet.getInt("production_year"));
                    movie.setGenre(resSet.getString("genre_name"));
                    movie.setAgeRestriction(resSet.getInt("age_restriction"));
                    movie.setDuration(Duration.ofMinutes(resSet.getInt("duration_minutes")));
                    movie.setImagePath(resSet.getString("image_path"));
                }
                else {
                    log.warn("No `movie` objects by given `id`= " + id +" attribute were found");
                }

            }
        } catch (SQLException e) {
            log.error("SQLException in MovieDao.getMovieById() " + e.getMessage());
            throw new DaoException("Couldn't find film by given id (" + id +")", e);
        }
        return movie;
    }

    public void addMovie(Movie movie) {
        log.info("Adding new movie to DB");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.MoviesSeancesQuery.ADD_MOVIE)) {

            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getDirector());
            statement.setInt(3, movie.getProductionYear());

            try {
                statement.setInt(4, Integer.parseInt(movie.getGenre()));
            } catch (NumberFormatException e) {
                log.error("Genre in movie object contained value that cannot be converted into int");
                throw new DaoException(e);
            }

            statement.setInt(5, (int)movie.getDuration().getSeconds()/60);
            statement.setInt(6, movie.getAgeRestriction());
            statement.setString(7, movie.getImagePath());

            statement.execute();

        } catch (SQLException e) {
            log.error("SQLException in MovieDao.addMovie() " + e.getMessage());
            throw new DaoException("Couldn't add movie to DB", e);
        }

    }

    public boolean deleteMovie(int movieId) {
        log.info("Deleting movie with id " + movieId);

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.MoviesSeancesQuery.DELETE_MOVIE)) {

            statement.setInt(1, movieId);

            statement.execute();
            log.info("Deleting movie ("+ movieId + ") from DB was successfully finished");
            return true;
        } catch (SQLException e) {
            log.error("SQLException in MovieDao.deleteMovie() " + e.getMessage());
            throw new DaoException("Couldn't delete movie", e);
        }
    }


    public int getMovieQuantity() {
        log.info("Counting movies ");
        int numberOfMovies = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.MoviesSeancesQuery.COUNT_MOVIES)) {
            try (ResultSet resSet = statement.executeQuery()) {
                if (resSet.next()) {
                    numberOfMovies = resSet.getInt("count");
                    log.info("Successfully got number of movies");
                }
            }

        } catch (SQLException e) {
            log.error("SQLException in SeanceDao.getMovieQuantity() " + e.getMessage());
            throw new DaoException("Couldn't get number of movies", e);
        }
        return numberOfMovies;
    }
}
