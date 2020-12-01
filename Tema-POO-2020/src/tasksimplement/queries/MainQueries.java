package tasksimplement.queries;

import entities.*;
import fileio.ActionInputData;
import fileio.Writer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.IOException;
import java.util.*;

public final class MainQueries {
    private MainQueries() { }

    /**
     *Here are implemented methods for the query cases in the main function
     * The first 4 methods are for movies (for rating, favorite, long and most viewed cases) , the
     * next for are the same but for shows and the remaining are for the actor queries
     * movieRating method sorts and prints the list for the movie rating query
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

            JSONObject object = fileWriter.writeFile(actionInputData.getActionId(),
                             "field", "Query result: " + listFinal);
            arrayResult.add(object);
        }
    }

    /**
     *movieFavorite method sorts and prints (in JSON array) the list for the movie favorite query
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

            JSONObject object = fileWriter.writeFile(action.getActionId(),
                            "field", "Query result: " + listFinal);
            arrayResult.add(object);
        }
    }

    /**
     *movieLong method sorts and prints the list for the movie long query
     */
    public static void movieLong(final List<User> users,
                                     final List<Movie> movies,
                                     final JSONArray arrayResult,
                                     final ActionInputData actionInputData,
                                     final Writer fileWriter) throws IOException {
        String year = actionInputData.getFilters().get(0).get(0);
        String genre = actionInputData.getFilters().get(1).get(0);
        List<String> listFinal;
        listFinal = Queries.returnListToSortLong(year, genre, movies, users);

        JSONObject object = fileWriter.writeFile(actionInputData.getActionId(),
                        "field", "Query result: " + listFinal);
        arrayResult.add(object);

    }

    /**
     *movieMostViewed method sorts and prints the list for the movie most_viewed query
     */
    public static void movieMostViewed(final List<User> users,
                                 final List<Movie> movies,
                                 final JSONArray arrayResult, final Action action,
                                 final ActionInputData actionInputData,
                                 final Writer fileWriter) throws IOException {

        String year = actionInputData.getFilters().get(0).get(0);
        String genre = actionInputData.getFilters().get(1).get(0);
        Map<String, Integer> listToSort;
        listToSort = Queries.returnListToSortMostViewed(year, genre, movies, users);

        if (action.getSortType().equals("desc")) {
            List<String> listFinal;
            listFinal = Queries.descSortedFavourite(listToSort);

            JSONObject object = fileWriter.writeFile(actionInputData.getActionId(),
                                "field", "Query result: " + listFinal);
            arrayResult.add(object);
        }
    }

    /**
     *showsRatings method sorts and prints the list for the show rating query
     */
    public static void showsRatings(final List<User> users,
                                    final List<Serial> serials,
                                    final JSONArray arrayResult, final Action action,
                                    final ActionInputData actionInputData,
                                    final Writer fileWriter) throws IOException {
        String year = actionInputData.getFilters().get(0).get(0);
        String genre = actionInputData.getFilters().get(1).get(0);
        Map<String, Double> listToSort;
        listToSort = Queries.returnShowsListToSortRating(year, genre, serials, users);

        if (action.getSortType().equals("desc")) {
            List<String> listFinal;
            listFinal = Queries.descSortedRating(listToSort);

            JSONObject object = fileWriter.writeFile(actionInputData.getActionId(),
                            "field", "Query result: " + listFinal);
            arrayResult.add(object);
        }
    }

    /**
     *showsFavorite method sorts and prints the list for the show favorite query
     */
    public static void showsFavorite(final List<User> users,
                                       final List<Serial> serials,
                                       final JSONArray arrayResult, final Action action,
                                       final ActionInputData actionInputData,
                                       final Writer fileWriter) throws IOException {
        String year = actionInputData.getFilters().get(0).get(0);
        String genre = actionInputData.getFilters().get(1).get(0);
        Map<String, Integer> listToSort;
        listToSort = Queries.sortedShowFavourites(year, genre, serials, users);

        List<String> listFinal;
        if (action.getSortType().equals("asc")) {
            listFinal = Queries.ascSortedFavourite(listToSort);
        } else {
            listFinal = Queries.descSortedFavourite(listToSort);
        }

        JSONObject object = fileWriter.writeFile(actionInputData.getActionId(),
                        "field", "Query result: " + listFinal);
        arrayResult.add(object);
    }

    /**
     *showLong method sorts and prints the list for the show long query
     */
    public static void showsLong(final List<User> users,
                                     final List<Serial> serials,
                                     final JSONArray arrayResult, final Action action,
                                     final ActionInputData actionInputData,
                                     final Writer fileWriter) throws IOException {
        String year = actionInputData.getFilters().get(0).get(0);
        String genre = actionInputData.getFilters().get(1).get(0);
        Map<String, Integer> listToSort;
        listToSort = Queries.sortedShowLongest(year, genre, serials, users);

        List<String> listFinal;
        if (action.getSortType().equals("asc")) {
            listFinal = Queries.ascSortedFavourite(listToSort);
        } else {
            listFinal = Queries.descSortedFavourite(listToSort);
        }

        JSONObject object = fileWriter.writeFile(actionInputData.getActionId(),
                            "field", "Query result: " + listFinal);
        arrayResult.add(object);
    }

    /**
     *showMostViewed method sorts and prints the list for the show most_viewed query
     */
    public static void showsMostViewed(final List<User> users,
                                 final List<Serial> serials,
                                 final JSONArray arrayResult, final Action action,
                                 final ActionInputData actionInputData,
                                 final Writer fileWriter) throws IOException {
        String year = actionInputData.getFilters().get(0).get(0);
        String genre = actionInputData.getFilters().get(1).get(0);
        Map<String, Integer> listToSort;
        listToSort = Queries.sortedShowMostViewed(year, genre, serials, users);

        List<String> listFinal;
        if (actionInputData.getSortType().equals("asc")) {
            listFinal = Queries.ascSortedFavourite(listToSort);
        } else {
            listFinal = Queries.descSortedFavourite(listToSort);
        }

        JSONObject object = fileWriter.writeFile(actionInputData.getActionId(),
                           "field", "Query result: " + listFinal);
        arrayResult.add(object);
    }



    /**
     *awards method sorts and prints the list for the actors awards query
     */
    public static void awards(final List<Actor> actors,
                                   final JSONArray arrayResult,
                                   final ActionInputData actionInputData,
                                   final Writer fileWriter) throws IOException {
        Map<String, Integer> listToSort;
        listToSort = ActorQueries.actorAwards(actors, actionInputData);

        List<String> listFinal;
        if (actionInputData.getSortType().equals("asc")) {
            listFinal = Queries.ascSortedFavourite(listToSort);
        } else {
            listFinal = Queries.descSortedFavourite(listToSort);
        }

        JSONObject object = fileWriter.writeFile(actionInputData.getActionId(),
                            "field", "Query result: " + listFinal);
            arrayResult.add(object);

    }

    /**
     *filter method sorts and prints the list for the actors filter_description query
     */
    public static void filter(final List<Actor> actors,
                             final JSONArray arrayResult, final Action action,
                             final ActionInputData actionInputData,
                             final Writer fileWriter) throws IOException {
        String year = actionInputData.getFilters().get(0).get(0);
        String genre = actionInputData.getFilters().get(1).get(0);
        ArrayList<String> listToSort;
        listToSort = ActorQueries.filterDescription(actors, actionInputData);

        if (actionInputData.getSortType().equals("asc")) {
           Collections.sort(listToSort);
        } else {
            Collections.sort(listToSort, Collections.reverseOrder());
        }

        JSONObject object = fileWriter.writeFile(actionInputData.getActionId(),
                          "field", "Query result: " + listToSort);
        arrayResult.add(object);

    }

}
