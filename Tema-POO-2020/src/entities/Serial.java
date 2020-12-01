package entities;

import entertainment.Season;
import fileio.SerialInputData;

import java.util.ArrayList;

public final class Serial {
    private final String title;
    private final int year;
    private final ArrayList<String> cast;
    private final ArrayList<String> genres;

    private final int numberOfSeasons;
    private final ArrayList<Season> seasons;

    private double generalRating;


    public Serial(final SerialInputData serial) {
        this.title = serial.getTitle();
        this.year = serial.getYear();
        this.cast = serial.getCast();
        this.genres = serial.getGenres();
        this.numberOfSeasons = serial.getNumberSeason();
        this.seasons = serial.getSeasons();
        this.generalRating = 0;
    }

    /*public double calcRating(final Serial serial) {
        double sumSerial = 0;

        for (int i = 0; i < serial.getNumberOfSeasons(); i++) {
            int sizeSeason = serial.getSeasons().get(i).getRatings().size();
            double sumSeason = 0;
            for (int j = 0; j < sizeSeason; j++) {
                double seasonRating = serial.getSeasons().get(i).getRatings().get(j);
                sumSeason = sumSeason + seasonRating;
            }
            sumSeason = sumSeason / sizeSeason;
            sumSerial = sumSerial + sumSeason;
        }

        sumSerial = sumSerial / serial.getNumberOfSeasons();
        serial.generalRating = sumSerial;
        return sumSerial;
    }*/


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
