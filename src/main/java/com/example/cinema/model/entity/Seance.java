package com.example.cinema.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Seance {
    private int id;
    private Movie movie;
    private LocalDateTime startTime;

    public Seance() { }

    public Seance(int id, Movie movie, LocalDateTime startTime) {
        this.id = id;
        this.movie = movie;
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartDate(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
