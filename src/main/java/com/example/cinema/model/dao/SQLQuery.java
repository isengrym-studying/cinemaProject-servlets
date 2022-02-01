package com.example.cinema.model.dao;

public class SQLQuery {
    static class UserQuery {
        public static final String FIND_USER_BY_EMAIL = "SELECT * FROM `users` WHERE `email`=?";
        public static final String FIND_USER_BY_EMAIL_PASSWORD = "SELECT * FROM `users` WHERE `email`=? AND `password` =?";
        public static final String GET_SALT_BY_EMAIL = "SELECT `salt` FROM `users` WHERE `email`=?";
        public static final String INSERT_USER = "INSERT INTO `users` VALUES (DEFAULT,?,?,?,?,?,?)";

        public static final String GET_ALL_MOVIES = "SELECT * FROM `movies`";
        public static final String GET_MOVIE_BY_ID = "SELECT * FROM `movies` INNER JOIN `genres` ON `genre`=`genre_id` WHERE `movie_id` = ?";

        public static final String GET_ALL_SEANCES = "SELECT * FROM `seances` INNER JOIN `movies` ON `seances`.`movie_id` = `movies`.`movie_id`";
        public static final String GET_SEANCES_FOR_MOVIE = "SELECT * FROM `seances` WHERE `movie_id`=? ORDER BY `day`";


    }
}
