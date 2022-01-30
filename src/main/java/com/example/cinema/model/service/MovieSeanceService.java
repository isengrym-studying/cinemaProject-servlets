package com.example.cinema.model.service;

import com.example.cinema.model.dao.UserDao;
import com.example.cinema.model.entity.Movie;
import com.example.cinema.model.entity.Seance;

import java.util.LinkedList;
import java.util.List;

public class MovieSeanceService {
    private static MovieSeanceService movieSeanceService;

    public static synchronized MovieSeanceService getInstance() {
        if (movieSeanceService == null) {
            movieSeanceService = new MovieSeanceService();
        }
        return movieSeanceService;
    }

    private MovieSeanceService() { }

    public List<Movie> getUniqueMovies(List<Seance> seanceList) {
        List<Movie> movieList = new LinkedList<>();
        for (Seance seance : seanceList) {
            if (!movieList.contains(seance.getMovie())) movieList.add(seance.getMovie());
        }
        return movieList;
    }
}
