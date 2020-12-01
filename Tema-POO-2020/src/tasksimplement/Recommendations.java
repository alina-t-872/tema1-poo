package tasksimplement;

import entities.Action;
import entities.Movie;
import entities.Serial;
import entities.User;
import fileio.Writer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.IOException;
import java.util.*;

public final class Recommendations {
    private Recommendations() { }
    /**
     * Here are implemented methods for the Recommendations part of the homework
     * findMovie prints the first movie that the user hasn't seen (it doesn't exists in the user's
     * history)
     */
    public static void findMovie(final List<User> users, final List<Movie> movies,
                                 final JSONArray arrayResult, final Writer fileWriter,
                                 final Action action) throws IOException {
        String movieNotSeen = new String();

        for (int j = 0; j < users.size(); j++) {

            if (users.get(j).getUsername().equals(action.getUsername())) {

                for (int k = 0; k < movies.size(); k++) {

                    if (!users.get(j).getHistory().containsKey(movies.get(k).getTitle())) {

                        movieNotSeen = movies.get(k).getTitle();
                        break;
                    }
                }
            }
        }
        JSONObject object = fileWriter.writeFile(action.getActionId(), "field",
                    "StandardRecommendation result: " + movieNotSeen);
        arrayResult.add(object);
    }

    /**
     *search method searches for all the movies and shows in the database that the user hasn't
     * seen yet, sorts them alphabetically according to the given criteria and prints them
     * This can be apllied only for premium users, otherwise it prints the message :
     * "SearchRecommendation cannot be applied!"
     */
    public static void search(final List<User> users, final Action action,
                              final List<Movie> movies, final List<Serial> serials,
                              final JSONArray arrayResult,
                              final Writer fileWriter) throws IOException {
        ArrayList<String> moviesNotSeen = new ArrayList<>();

        for (int j = 0; j < users.size(); j++) {
            if (users.get(j).getUsername().equals(action.getUsername())) {
                if (users.get(j).getSubscriptionType().equals("PREMIUM")) {

                    for (int k = 0; k < movies.size(); k++) {
                        if (!users.get(j).getHistory().containsKey(movies.get(k).getTitle())) {

                            if (movies.get(k).getGenres().contains(action.getGenre())) {
                                moviesNotSeen.add(movies.get(k).getTitle());
                            }
                        }
                    }
                    for (int k = 0; k < serials.size(); k++) {
                        if (!users.get(j).getHistory().containsKey(serials.get(k).getTitle())) {
                            if (serials.get(k).getGenres().contains(action.getGenre())) {
                                moviesNotSeen.add(serials.get(k).getTitle());
                            }
                        }
                    }
                    Collections.sort(moviesNotSeen);
                }
                if (moviesNotSeen.isEmpty()) {

                    JSONObject object = fileWriter.writeFile(action.getActionId(),
                                   "field", "SearchRecommendation cannot be applied!");
                    arrayResult.add(object);
                } else {

                    JSONObject object = fileWriter.writeFile(action.getActionId(),
                                    "field", "SearchRecommendation result: "
                                    + moviesNotSeen);
                    arrayResult.add(object);
                }

            }
        }
    }

    /**
     *favourite method creates a map that holds every movie and show and its number of
     * appearances in a favourite list of a user
     * Then it is sorted alphabetically and by value and then printed (only the title of the
     * video is printed)
     */
    public static void favorite(final List<User> users, final Action action,
                              final List<Movie> movies, final List<Serial> serials,
                              final JSONArray arrayResult,
                              final Writer fileWriter) throws IOException {
        Map<String, Integer> favouriteMovies = new HashMap<>();

        for (int k = 0; k < users.size(); k++) {

            for (int j = 0; j < movies.size(); j++) {
                int cnt = 0;
                if (users.get(k).getFavoriteMovies().contains(movies.get(j).getTitle())) {
                    cnt++;
                }
                if (cnt != 0) {
                    favouriteMovies.put(movies.get(j).getTitle(), cnt);
                }
            }

            for (int j = 0; j < serials.size(); j++) {
                int cnt = 0;
                if (users.get(k).getFavoriteMovies().contains(serials.get(j).getTitle())) {
                    cnt++;
                }
                if (cnt != 0) {
                    favouriteMovies.put(serials.get(j).getTitle(), cnt);
                }
            }
        }

        LinkedHashMap<String, Integer> sortFavourite = new LinkedHashMap();
        favouriteMovies.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEachOrdered(v -> sortFavourite.put(v.getKey(), v.getValue()));

        String filmName = new String();
        for (int j = 0; j < users.size(); j++) {
            if (users.get(j).getUsername().equals(action.getUsername())) {
                for (Map.Entry<String, Integer> entry : sortFavourite.entrySet()) {

                    if (!users.get(j).getHistory().containsKey(entry.getKey())) {
                        filmName = entry.getKey();
                        break;
                    }
                }

                JSONObject object = fileWriter.writeFile(action.getActionId(),
                                "field", "FavoriteRecommendation "
                                 + "result: " + filmName);
                arrayResult.add(object);
                break;
            }
        }
    }
}

