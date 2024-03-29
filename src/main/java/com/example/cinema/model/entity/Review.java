package com.example.cinema.model.entity;

import java.time.LocalDateTime;

public class Review {
    private int id;
    private int movieId;
    private User user;
    private String text;
    private LocalDateTime date;

    public Review() {}

    public Review(int id, int movieId, User user, String text, LocalDateTime date) {
        this.id = id;
        this.movieId = movieId;
        this.user = user;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", user=" + user +
                '}';
    }
}
