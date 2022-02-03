package com.example.cinema.model.entity;

import java.time.Duration;
import java.util.Objects;

/**
 * Class represents 'movie' entity.
 *
 */
public class Movie {
    private int id;
    private String title;
    private String director;
    private int productionYear;
    private String genre;
    private int ageRestriction;
    private Duration duration;
    private String imagePath;

    public Movie(int id, String title, String director, int productionYear, String genre, int ageRestriction, Duration duration, String imagePath) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.productionYear = productionYear;
        this.genre = genre;
        this.ageRestriction = ageRestriction;
        this.duration = duration;
        this.imagePath = imagePath;
    }

    public Movie() { }


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() {return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() { return director; }

    public void setDirector(String director) { this.director = director; }

    public int getProductionYear() { return productionYear; }

    public void setProductionYear(int productionYear) { this.productionYear = productionYear; }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public int getAgeRestriction() { return ageRestriction; }

    public void setAgeRestriction(int ageRestriction) { this.ageRestriction = ageRestriction; }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
