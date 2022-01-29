package com.example.cinema.model.entity;

import java.time.Duration;

public class Movie {
    private String title;
    private String director;
    private int productionYear;
    private Duration duration;
    private String imagePath;

    public Movie(String title, String director, int productionYear, Duration duration, String imagePath) {
        this.title = title;
        this.director = director;
        this.productionYear = productionYear;
        this.duration = duration;
        this.imagePath = imagePath;
    }

    public Movie() { }

    public String getTitle() {return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() { return director; }

    public void setDirector(String director) { this.director = director; }

    public int getProductionYear() { return productionYear; }

    public void setProductionYear(int productionYear) { this.productionYear = productionYear; }

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
}
