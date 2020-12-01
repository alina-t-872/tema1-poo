package tasksimplement;

import entities.Action;
import entities.Movie;
import entities.Serial;
import entities.User;
import fileio.Writer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Recommendations {
    private Recommendations() { }

    /**
     *
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
        JSONObject object = fileWriter.writeFile(action.getActionId(),
                        "field", "StandardRecommendation result: " + movieNotSeen);
        arrayResult.add(object);
    }

    /**
     *
     */
    public static void search(final List<User> users, final Action action,
                              final List<Movie> movies, final List<Serial> serials,
                              final JSONArray arrayResult,
                              final Writer fileWriter) throws IOException {
        ArrayList<String> moviesNotSeen = new ArrayList<>();
        for (int j = 0; j < users.size(); j++) {
            if (users.get(j).getUsername().equals(action.getUsername())) {
                if (users.get(j).getSubscriptionType().equals(
                        "PREMIUM")) {
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
                    JSONObject object =
                            fileWriter.writeFile(action.getActionId(),
                                    "field", "SearchRecommendation cannot be applied!");
                    arrayResult.add(object);
                } else {
                    JSONObject object =
                            fileWriter.writeFile(action.getActionId(),
                                    "field", "SearchRecommendation result: "
                                    + moviesNotSeen
                            );
                    arrayResult.add(object);
                }

            }
        }
    }
}
