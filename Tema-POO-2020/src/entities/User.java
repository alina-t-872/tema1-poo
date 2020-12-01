package entities;

import fileio.UserInputData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class User {
    /**
     * The class fields
     * The ratedMovies fields represents a map that holds the name of the show or movie
     * which the user rated and its grade
     */
    private final String username;
    private final String subscriptionType;
    private final Map<String, Integer> history;
    private final ArrayList<String> favoriteMovies;
    private Map<String, Integer> ratedMovies = new HashMap<>();

    /**
     *Constructor for the class fields
     */
    public User(final UserInputData user) {
        this.username = user.getUsername();
        this.subscriptionType = user.getSubscriptionType();
        this.history = user.getHistory();
        this.favoriteMovies = user.getFavoriteMovies();
    }

    /**
     * Getters for the class fields because they are private
     */
    public String getUsername() {
        return username;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    public Map<String, Integer> getRatedMovies() {
        return ratedMovies;
    }

    /**
     * Overriding the method toString to print the class fields
     */
    @Override
    public String toString() {
        return "User{"
                + "username='" + username + '\''
                + ", subscriptionType='" + subscriptionType + '\''
                + ", history=" + history
                + ", favoriteMovies=" + favoriteMovies
                + '}';
    }
}
