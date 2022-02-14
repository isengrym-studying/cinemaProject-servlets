package com.example.cinema.service;

import com.example.cinema.model.dao.GenreDao;
import com.example.cinema.model.entity.Genre;

import java.util.List;

/**
 * The class contains all the logic for working with genres.
 * This layer is located between DAO and Controller layers
 *
 */
public class GenreService {
    private final GenreDao genreDao = GenreDao.getInstance();
    private static GenreService genreService;

    public static synchronized GenreService getInstance() {
        if (genreService == null) {
            genreService = new GenreService();
        }
        return genreService;
    }


    /**
     * Method for getting all genres. Method just resends to
     * the relevant DAO method and contains no
     * @return Returns filled List<Genre> (If there are genres in DB).
     * Returns empty List<Genre> (If there are no genres in DB).
     *
     */
    public List<Genre> getAllGenres() {
        return genreDao.getAllGenres();
    }
}
