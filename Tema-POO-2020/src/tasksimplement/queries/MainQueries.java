package tasksimplement.queries;

import entities.Action;
import entities.Movie;
import entities.Serial;
import entities.User;
import fileio.ActionInputData;
import fileio.MovieInputData;
import fileio.Writer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MainQueries {
    private MainQueries() { }

    /**
     *
     */
    public static void movieRatings(final List<User> users,
                              final List<Movie> movies,
                              final JSONArray arrayResult, final Action action,
                              final ActionInputData actionInputData,
                              final Writer fileWriter) throws IOException {
        String year = actionInputData.getFilters().get(0).get(0);
        String genre = actionInputData.getFilters().get(1).get(0);
        Map<String, Double> listToSort = new HashMap<>();
        listToSort = Queries.returnListToSortRating(year, genre, movies, users);

        if (action.getSortType().equals("desc")) {
            List<String> listFinal = new ArrayList<>();
            listFinal = Queries.descSortedRating(listToSort);

            JSONObject object =
                    fileWriter.writeFile(actionInputData.getActionId(),
                            "field", "Query result: " + listFinal);
            arrayResult.add(object);
        }
    }

    /**
     *
     */
    public static void movieFavorite(final List<User> users,
                                   final List<Movie> movies,
                                   final JSONArray arrayResult, final Action action,
                                   final ActionInputData actionInputData,
                                   final Writer fileWriter) throws IOException {
        String year = actionInputData.getFilters().get(0).get(0);
        String genre = actionInputData.getFilters().get(1).get(0);
        Map<String, Integer> listToSort = new HashMap<>();
        listToSort = Queries.returnListToSort(year, genre, movies, users);

        if (action.getSortType().equals("desc")) {

            List<String> listFinal = new ArrayList<>();
            listFinal = Queries.descSortedFavourite(listToSort);

            JSONObject object = fileWriter.writeFile(action.getActionId(),
                    "field", "Query result: " + listFinal);
            arrayResult.add(object);

        } else {

            List<String> listFinal = new ArrayList<>();
            listFinal = Queries.ascSortedFavourite(listToSort);

            JSONObject object =
                    fileWriter.writeFile(action.getActionId(),
                            "field", "Query result: " + listFinal
                    );
            arrayResult.add(object);
        }
    }

    /**
     *
     */
    public static void movieLong(final List<User> users,
                                     final List<Movie> movies,
                                     final JSONArray arrayResult,
                                     final ActionInputData actionInputData,
                                     final Writer fileWriter) throws IOException {
        String year = actionInputData.getFilters().get(0).get(0);
        String genre = actionInputData.getFilters().get(1).get(0);
        List<String> listFinal = new ArrayList<>();
        listFinal = Queries.returnListToSortLong(year, genre, movies, users);

        JSONObject object =
                fileWriter.writeFile(actionInputData.getActionId(),
                        "field", "Query result: " + listFinal);
        arrayResult.add(object);

    }
    /**
     *
     */
    public static void movieMostViewed(final List<User> users,
                                 final List<Movie> movies,
                                 final JSONArray arrayResult, final Action action,
                                 final ActionInputData actionInputData,
                                 final Writer fileWriter) throws IOException {

        String year = actionInputData.getFilters().get(0).get(0);
        String genre = actionInputData.getFilters().get(1).get(0);
        Map<String, Integer> listToSort = new HashMap<>();
        listToSort = Queries.returnListToSortMostViewed(year, genre, movies, users);

        if (action.getSortType().equals("desc")) {
            List<String> listFinal = new ArrayList<>();
            listFinal = Queries.descSortedFavourite(listToSort);

            JSONObject object =
                    fileWriter.writeFile(actionInputData.getActionId(),
                            "field", "Query result: " + listFinal);
            arrayResult.add(object);
        }
    }

    /**
     *
     */
    public static void showsFavorite(final List<User> users,
                                       final List<Serial> serials,
                                       final JSONArray arrayResult, final Action action,
                                       final ActionInputData actionInputData,
                                       final Writer fileWriter) throws IOException {
        String year = actionInputData.getFilters().get(0).get(0);
        String genre = actionInputData.getFilters().get(1).get(0);
        Map<String, Integer> listToSort = new HashMap<>();
        listToSort = Queries.sortedShowFavourites(year, genre, serials, users);

        List<String> listFinal = new ArrayList<>();
        if (action.getSortType().equals("asc")) {
            listFinal = Queries.ascSortedFavourite(listToSort);
        } else {
            listFinal = Queries.descSortedFavourite(listToSort);
        }

        JSONObject object =
                fileWriter.writeFile(actionInputData.getActionId(),
                        "field", "Query result: " + listFinal
                );
        arrayResult.add(object);
    }

    /**
     *
     */
    public static void showsLong(final List<User> users,
                                     final List<Serial> serials,
                                     final JSONArray arrayResult, final Action action,
                                     final ActionInputData actionInputData,
                                     final Writer fileWriter) throws IOException {
        String year = actionInputData.getFilters().get(0).get(0);
        String genre = actionInputData.getFilters().get(1).get(0);
        Map<String, Integer> listToSort = new HashMap<>();
        listToSort = Queries.sortedShowLongest(year, genre, serials, users);

        List<String> listFinal = new ArrayList<>();
        if (action.getSortType().equals("asc")) {
            listFinal = Queries.ascSortedFavourite(listToSort);
        } else {
            listFinal = Queries.descSortedFavourite(listToSort);
        }

        JSONObject object =
                fileWriter.writeFile(actionInputData.getActionId(),
                        "field", "Query result: " + listFinal);
        arrayResult.add(object);
    }

    /**
     *
     */
    public static void showsMostViewed(final List<User> users,
                                 final List<Serial> serials,
                                 final JSONArray arrayResult, final Action action,
                                 final ActionInputData actionInputData,
                                 final Writer fileWriter) throws IOException {
        String year = actionInputData.getFilters().get(0).get(0);
        String genre = actionInputData.getFilters().get(1).get(0);
        Map<String, Integer> listToSort = new HashMap<>();
        listToSort = Queries.sortedShowMostViewed(year, genre, serials, users);

        List<String> listFinal = new ArrayList<>();
        if (actionInputData.getSortType().equals("asc")) {
            listFinal = Queries.ascSortedFavourite(listToSort);
        } else {
            listFinal = Queries.descSortedFavourite(listToSort);
        }

        JSONObject object =
                fileWriter.writeFile(actionInputData.getActionId(),
                        "field", "Query result: " + listFinal);
        arrayResult.add(object);
    }
}
