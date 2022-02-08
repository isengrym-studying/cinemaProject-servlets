package com.example.cinema.model.service;

import com.example.cinema.model.dao.GenreDao;
import com.example.cinema.model.dao.TicketDao;
import com.example.cinema.model.entity.Genre;

import java.util.List;

public class GenreService {
    private final GenreDao genreDao = GenreDao.getInstance();
    private static GenreService genreService;

    public static synchronized GenreService getInstance() {
        if (genreService == null) {
            genreService = new GenreService();
        }
        return genreService;
    }


    public List<Genre> getAllGenres() {
        return genreDao.getAllGenres();
    }
}
