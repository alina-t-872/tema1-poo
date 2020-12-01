package entities;

import entertainment.Season;
import fileio.SerialInputData;

import java.util.ArrayList;

public final class Serial {
    /**
     * The class fields
     * The generalRating field represents the general rating for the show
     */
    private final String title;
    private final int year;
    private final ArrayList<String> cast;
    private final ArrayList<String> genres;
    private final int numberOfSeasons;
    private final ArrayList<Season> seasons;
    private double generalRating;

    /**
     *Constructor for the class fields
     */
    public Serial(final SerialInputData serial) {
        this.title = serial.getTitle();
        this.year = serial.getYear();
        this.cast = serial.getCast();
        this.genres = serial.getGenres();
        this.numberOfSeasons = serial.getNumberSeason();
        this.seasons = serial.getSeasons();
        this.generalRating = 0;
    }

    /**
     * Getters & Setters for the class fields because they are private
     */
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

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public double getGeneralRating() {
        return generalRating;
    }

    public void setGeneralRating(final double generalRating) {
        this.generalRating = generalRating;
    }

    /**
     * Overriding the method toString to print the class fields
     */
    @Override
    public String toString() {
        return "Serial{"
                + "title='" + title + '\''
                + ", year=" + year
                + ", cast=" + cast
                + ", genres=" + genres
                + ", numberOfSeasons=" + numberOfSeasons
                + ", seasons=" + seasons
                /*+ ", rating=" + generalRating*/
                + '}';
    }
}
