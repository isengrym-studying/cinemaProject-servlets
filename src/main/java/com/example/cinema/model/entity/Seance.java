package com.example.cinema.model.entity;

import java.time.LocalDateTime;

public class Seance {
    private int id;
    private Movie movie;
    private LocalDateTime startDate;

    public Seance() { }

    public Seance(int id, Movie movie, LocalDateTime startDate) {
        this.id = id;
        this.movie = movie;
        this.startDate = startDate;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startTime) { this.startDate = startTime; }

}
