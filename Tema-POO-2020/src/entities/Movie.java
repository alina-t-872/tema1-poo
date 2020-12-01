package entities;

import fileio.MovieInputData;

import java.util.ArrayList;

public final class Movie {
    /**
     * The class fields
     */
    private final String title;
    private final int year;
    private final ArrayList<String> cast;
    private final ArrayList<String> genres;
    private final int duration;
    private double rating;

    /**
     *Constructor for the class fields
     */
    public Movie(final MovieInputData movieInputData) {
        this.title = movieInputData.getTitle();
        this.year = movieInputData.getYear();
        this.cast = movieInputData.getCast();
        this.genres = movieInputData.getGenres();
        this.duration = movieInputData.getDuration();
        this.rating = 0;
    }

    /**
     * Getters & Setters for the class fields because they are private
     * The method calcRating is used for calculating the rating of the movie given by the user
     */
    public double calcRating(final double rating) {
        this.rating = rating;
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public int getDuration() {
        return duration;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(final double rating) {
        this.rating = rating;
    }

    /**
     * Overriding the method toString to print the class fields
     */
    @Override
    public String toString() {
        return "Movie{"
                + "title='" + title + '\''
                + ", year=" + year
                + ", cast=" + cast
                + ", genres=" + genres
                + ", duration=" + duration
                + ", rating=" + rating
                + '}';
    }
}
