package com.example.cinema.model.dao;

/**
 * Class contains SQL-queries for DB
 *
 */
public class SQLQuery {
    static class UserQuery {
        public static final String FIND_USER_BY_EMAIL = "SELECT * FROM `users` WHERE `email`=?";
        public static final String FIND_USER_BY_EMAIL_PASSWORD = "SELECT * FROM `users` WHERE `email`=? AND `password` =?";
        public static final String GET_SALT_BY_EMAIL = "SELECT `salt` FROM `users` WHERE `email`=?";
        public static final String INSERT_USER = "INSERT INTO `users` VALUES (DEFAULT,?,?,?,?,?,?)";
        public static final String UPDATE_USER = "UPDATE `users` SET `name` = ?, `surname` = ?, `email` = ?, `password` = ? WHERE `user_id` = ?";
        public static final String DELETE_USER = "DELETE FROM `users` WHERE `user_id` = ?";
    }
    static class MoviesSeancesQuery {
        public static final String GET_ALL_MOVIES = "SELECT * FROM `movies`";
        public static final String GET_ALL_MOVIES_PAGINATED = "SELECT * FROM `movies` LIMIT ?,?";
        public static final String GET_MOVIE_BY_ID = "SELECT * FROM `movies` INNER JOIN `genres` ON `genre`=`genre_id` WHERE `movie_id` = ?";
        public static final String GET_UNIQUE_FUTURE_SEANCES_PAGINATED = "SELECT * FROM `seances` INNER JOIN `movies` ON `seances`.`movie_id` = `movies`.`movie_id`" +
                "WHERE `startDateSeconds`>= ? GROUP BY `movies`.`movie_id` LIMIT ?,?";
        public static final String COUNT_UNIQUE_FUTURE_SEANCES = "SELECT COUNT(*) AS count FROM `seances` INNER JOIN `movies` ON `seances`.`movie_id` = `movies`.`movie_id`" +
                "WHERE `startDateSeconds`>= ? GROUP BY `movies`.`movie_id`";
        public static final String COUNT_FUTURE_SEANCES = "SELECT COUNT(*) AS count FROM `seances` INNER JOIN `movies` ON `seances`.`movie_id` = `movies`.`movie_id`" +
                "WHERE `startDateSeconds`>= ?";
        public static final String COUNT_MOVIES = "SELECT COUNT(`movie_id`) AS count FROM `movies`";
        public static final String GET_SEANCES_FOR_MOVIE = "SELECT * FROM `seances` WHERE `startDateSeconds`>= ? and `movie_id`=? ORDER BY `startDateSeconds`";
        public static final String GET_SEANCE_BY_ID = "SELECT * FROM `seances` INNER JOIN `movies` ON `seances`.`movie_id` = `movies`.`movie_id` WHERE `seance_id` = ?";
        public static final String ADD_MOVIE = "INSERT INTO `movies` VALUES(DEFAULT,?,?,?,?,?,?,?)";
        public static final String DELETE_MOVIE = "DELETE FROM `movies` WHERE `movie_id` = ?";
        public static final String ADD_SEANCE = "INSERT INTO `seances` VALUES(DEFAULT,?,?,?, DEFAULT)";;
        public static final String DELETE_SEANCE = "DELETE FROM `seances` WHERE `seance_id` = ?";
        public static final String GET_SEANCES_BY_DATE_PAGINATED = "SELECT * FROM `seances` INNER JOIN `movies` ON `seances`.`movie_id` = `movies`.`movie_id` " +
                "WHERE `startDateSeconds`>= ? ORDER BY `startDateSeconds` %s LIMIT ?,? ";
        public static final String GET_SEANCES_BY_FREE_PLACES_PAGINATED = "SELECT * FROM `seances` INNER JOIN `movies` ON `seances`.`movie_id` = `movies`.`movie_id` " +
                "WHERE `startDateSeconds`>= ? ORDER BY `free_places` %s LIMIT ?,? ";
        public static final String GET_SEANCES_FOR_MOVIE_BY_DATE_PAGINATED = "SELECT * FROM `seances` INNER JOIN `movies` ON `seances`.`movie_id` = `movies`.`movie_id` " +
                "WHERE `startDateSeconds`>= ? and `seances`.`movie_id`=? ORDER BY `startDateSeconds` %s LIMIT ?,? ";
        public static final String GET_SEANCES_FOR_MOVIE_BY_FREE_PLACES_PAGINATED = "SELECT * FROM `seances` INNER JOIN `movies` ON `seances`.`movie_id` = `movies`.`movie_id` " +
                "WHERE `startDateSeconds`>= ? and `seances`.`movie_id`=? ORDER BY `free_places` %s LIMIT ?,? ";
        public static final String COUNT_SEANCES_FOR_MOVIE = "SELECT COUNT(`seance_id`) AS count FROM `seances` WHERE `startDateSeconds`>= ? and `movie_id`=?";
    }
    static class TicketQuery {
        public static final String GET_TICKETS_FOR_SEANCE = "SELECT * FROM `tickets` WHERE `seance_id`=?";
        public static final String ADD_TICKET = "INSERT INTO `tickets` VALUES(DEFAULT,?,?,?,?)";


        public static final String GET_USER_TICKETS_PAGINATED =
                "((SELECT `tickets`.*,`seances`.`startDateSeconds` FROM `tickets` INNER JOIN `seances` ON `tickets`.`seance_id` = `seances`.`seance_id` WHERE `user_id`=? and `startDateSeconds`>=? ORDER BY `startDateSeconds` DESC) " +
                "UNION " +
                "(SELECT `tickets`.*,`seances`.`startDateSeconds` FROM `tickets` INNER JOIN `seances` ON `tickets`.`seance_id` = `seances`.`seance_id` WHERE `user_id`=? and `startDateSeconds`< ? ORDER BY `startDateSeconds` DESC)) LIMIT ?,?";

        public static final String COUNT_USER_TICKETS = "SELECT COUNT(*) AS count FROM `tickets` " +
                "INNER JOIN `seances` ON `tickets`.`seance_id` = `seances`.`seance_id` WHERE `user_id`=? and `startDateSeconds`>=?";
        public static final String SUBTRACT_ONE_PLACE = "UPDATE `seances` SET `free_places` = `free_places`-1 WHERE `seance_id` = ?";

    }

    static class GenresQuery {
        public static final String GET_ALL_GENRES = "SELECT * FROM `genres`";
    }

    static class ReviewsQuery {
        public static final String GET_REVIEW_BY_USER_AND_MOVIE = "SELECT `reviews`.*, `users`.name, `users`.surname FROM `reviews` " +
                "INNER JOIN `users` ON `reviews`.`user_id` = `users`.`user_id` WHERE `reviews`.`user_id`=? and `movie_id`=?";
        public static final String GET_REVIEWS_FOR_MOVIE_PAGINATED = "SELECT `reviews`.*, `users`.name, `users`.surname FROM `reviews` " +
                "INNER JOIN `users` ON `reviews`.`user_id` = `users`.`user_id` WHERE `reviews`.`movie_id`=? ORDER BY `review_date` DESC LIMIT ?,?";
        public static final String COUNT_REVIEWS_FOR_MOVIE = "SELECT COUNT(*) AS count FROM `reviews` WHERE `movie_id`=?";
        public static final String ADD_REVIEW = "INSERT INTO `reviews` VALUES(DEFAULT,?,?,?,?)";
        public static final String UPDATE_REVIEW = "UPDATE `reviews` SET `review_text`= ?, `review_date`=? WHERE `user_id` = ? and movie_id=?";
        public static final String DELETE_REVIEW = "DELETE FROM `reviews` WHERE `user_id`=? and `movie_id`=?";
    }
}
