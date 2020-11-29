package entities;

import fileio.ShowInput;

import java.util.ArrayList;

public final class Show {
    private final String title;
    private final int year;
    private final ArrayList<String> cast;
    private final ArrayList<String> genres;

    public Show(final ShowInput showInput) {
        this.title = showInput.getTitle();
        this.year = showInput.getYear();
        this.cast = showInput.getCast();
        this.genres = showInput.getGenres();
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
}
