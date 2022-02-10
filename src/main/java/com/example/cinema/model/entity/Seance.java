package com.example.cinema.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class represents 'seance' entity.
 *
 */
public class Seance {
    private int id;
    private Movie movie;
    private LocalDateTime startDate;
    private int ticketPrice;
    private int freePlaces;

    public Seance() {}

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

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getTicketPrice() { return ticketPrice; }

    public void setTicketPrice(int ticketPrice) { this.ticketPrice = ticketPrice; }

    public int getFreePlaces() { return freePlaces; }

    public void setFreePlaces(int freePlaces) { this.freePlaces = freePlaces; }

    public LocalDateTime getEndDate() { return this.getStartDate().plus(this.getMovie().getDuration()); }

    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seance seance = (Seance) o;
        return id == seance.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
