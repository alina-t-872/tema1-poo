package tasksimplement;

import entities.Movie;
import entities.Serial;
import entities.User;
import fileio.ActionInputData;
import fileio.ActorInputData;

import java.util.*;

public final class Queries {
    private Queries() { }

    /**
     *
     */
    public static void average(final List<ActorInputData> actors,
                               final ActionInputData action) {
        for (int i = 1; i <= action.getNumber(); i++) {
            if (action.getSortType() == "asc")  { //daca este crescator
            } else {   //daca este descrescators

            }
        }
    }

    /**
     *
     */
    public static Map<String, Integer> returnListToSort(final String year,
                  final String genre, final List<Movie> movies, final List<User> users) {
        Map<String, Integer> listToSort = new HashMap<>();
        Map<String, Integer> favouriteMovies = new HashMap<>();

        for (int j = 0; j < movies.size(); j++) {
            int cnt = 0;
            for (int k = 0; k < users.size(); k++) {
                if (users.get(k).getFavoriteMovies().contains(movies.get(j).getTitle())) {
                    cnt++;
                }
            }
            favouriteMovies.put(movies.get(j).getTitle(), cnt);
        }

        if (year == null && genre == null) {
            listToSort.putAll(favouriteMovies);
        } else {
            for (int j = 0; j < movies.size(); j++) {
                String sir =
                        String.valueOf(movies.get(j).getYear());
                if (sir.equals(year) && movies.get(j).getGenres().contains(genre)) {
                    for (Map.Entry<String, Integer> entry
                            : favouriteMovies.entrySet()) {
                        if (entry.getKey().equals(movies.get(j).getTitle())) {
                            listToSort.put(movies.get(j).getTitle(), entry.getValue());
                        }
                    }
                }
            }
        }
        return listToSort;

    }

    /**
     *
     */
    public static List<String> descSortedFavourite(final Map<String,
            Integer> listToSort) {
        LinkedHashMap<String, Integer> sortFavourite2 = new LinkedHashMap();

        listToSort.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEachOrdered(v -> sortFavourite2.put(v.getKey(), v.getValue()));
        LinkedHashMap<Integer, List<String>> finalList1 =
                new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry2
                : sortFavourite2.entrySet()) {
            int value = entry2.getValue();
            List<String> strings =
                    new ArrayList<>();
            for (Map.Entry<String, Integer> entry1 : sortFavourite2.entrySet()) {
                if (value == entry1.getValue()) {
                    strings.add(entry1.getKey());
                }

            }
            finalList1.put(entry2.getValue(), strings);
        }
        LinkedHashMap<Integer, List<String>> listToPrint =
                new LinkedHashMap<>();
        finalList1.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEachOrdered(v -> listToPrint.put(v.getKey(), v.getValue()));
        List<String> listFinal = new ArrayList<>();

        for (Map.Entry<Integer, List<String>> iterate
                : listToPrint.entrySet()) {
            listFinal.addAll(iterate.getValue());
        }
        return listFinal;
    }

    /**
     *
     */
    public static List<String> ascSortedFavourite(final Map<String,
            Integer> listToSort) {
        LinkedHashMap<String, Integer> sortFavourite2 = new LinkedHashMap();

        listToSort.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(v -> sortFavourite2.put(v.getKey(), v.getValue()));
        LinkedHashMap<Integer, List<String>> finalList1 =
                new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry2
                : sortFavourite2.entrySet()) {
            int value = entry2.getValue();
            List<String> strings =
                    new ArrayList<>();
            for (Map.Entry<String, Integer> entry1 : sortFavourite2.entrySet()) {
                if (value == entry1.getValue()) {
                    strings.add(entry1.getKey());
                }

            }
            finalList1.put(entry2.getValue(), strings);
        }
        LinkedHashMap<Integer, List<String>> listToPrint =
                new LinkedHashMap<>();
        finalList1.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(v -> listToPrint.put(v.getKey(), v.getValue()));
        List<String> listFinal = new ArrayList<>();

        for (Map.Entry<Integer, List<String>> iterate
                : listToPrint.entrySet()) {
            listFinal.addAll(iterate.getValue());
        }
        return listFinal;
    }

    /**
     *
     */
    public static List<String> returnListToSortLong(final String year,
                  final String genre, final List<Movie> movies, final List<User> users) {

        Map<String, Integer> listToSort = new HashMap<>();
        Map<String, Integer> durationMovie = new HashMap<>();
        for (int j = 0; j < movies.size(); j++) {
            durationMovie.put(movies.get(j).getTitle(),
                    movies.get(j).getDuration());
        }
        if (year == null && genre == null) {
            listToSort.putAll(durationMovie);
        } else {
            for (int j = 0; j < movies.size(); j++) {
                String sir =
                        String.valueOf(movies.get(j).getYear());
                if (sir.equals(year) && movies.get(j).getGenres().contains(genre)) {
                    listToSort.put(movies.get(j).getTitle(),
                            movies.get(j).getDuration());
                }
            }
        }

        List<String> listFinal = new ArrayList<>();

        for (Map.Entry<String, Integer> iterate : listToSort.entrySet()) {
            listFinal.add(iterate.getKey());
        }
        return listFinal;
    }

    /**
     *
     */
    public static Map<String, Integer> returnListToSortMostViewed(final String year,
                   final String genre, final List<Movie> movies, final List<User> users) {

        Map<String, Integer> mostViews = new HashMap<>();
        for (int j = 0; j < movies.size(); j++) {
            int nrViews = 0;
            for (int k = 0; k < users.size(); k++) {
                if (users.get(k).getHistory().containsKey(movies.get(j).getTitle())) {
                    for (Map.Entry<String, Integer> entry : users.get(k).getHistory().entrySet()) {
                        if (movies.get(j).getTitle().equals(entry.getKey())) {
                            nrViews = nrViews + entry.getValue();
                        }
                    }
                }
            }
            if (nrViews != 0) {
                mostViews.put(movies.get(j).getTitle(), nrViews);
            }
        }
        Map<String, Integer> listToSort = new HashMap<>();
        if (year == null && genre == null) {
            listToSort.putAll(mostViews);
        } else {
            for (int j = 0; j < movies.size(); j++) {
                String sir = String.valueOf(movies.get(j).getYear());
                if (sir.equals(year) && movies.get(j).getGenres().contains(genre)) {
                    for (Map.Entry<String, Integer> entry : mostViews.entrySet()) {
                        if (entry.getKey().equals(movies.get(j).getTitle())) {
                            listToSort.put(movies.get(j).getTitle(), entry.getValue());
                        }
                    }
                }
            }
        }
        return listToSort;
    }
    /**
     *
     */
    public static Map<String, Double> returnListToSortRating(final String year,
                  final String genre, final List<Movie> movies, final List<User> users) {
        Map<String, Double> movieRatings = new HashMap<>();
        for (int j = 0; j < movies.size(); j++) {
            if (movies.get(j).getRating() != 0) {
                movieRatings.put(movies.get(j).getTitle(), movies.get(j).getRating());
            }
        }
        Map<String, Double> listToSort = new HashMap<>();

        if (year == null && genre == null) {
            listToSort.putAll(movieRatings);
        } else {
            for (int j = 0; j < movies.size(); j++) {
                String sir =
                        String.valueOf(movies.get(j).getYear());
                if (sir.equals(year) && movies.get(j).getGenres().contains(genre)) {
                    listToSort.put(movies.get(j).getTitle(),
                            movies.get(j).getRating());
                }
            }
        }
        return listToSort;
    }

    /**
     *
     */
    public static List<String> descSortedRating(final Map<String,
            Double> listToSort) {
        LinkedHashMap<String, Double> sortFavourite2 = new LinkedHashMap();

        listToSort.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEachOrdered(v -> sortFavourite2.put(v.getKey(), v.getValue()));
        LinkedHashMap<Double, List<String>> finalList1 =
                new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry2
                : sortFavourite2.entrySet()) {
            double value = entry2.getValue();
            List<String> strings =
                    new ArrayList<>();
            for (Map.Entry<String, Double> entry1 : sortFavourite2.entrySet()) {
                if (value == entry1.getValue()) {
                    strings.add(entry1.getKey());
                }

            }
            finalList1.put(entry2.getValue(), strings);
        }
        LinkedHashMap<Double, List<String>> listToPrint =
                new LinkedHashMap<>();
        finalList1.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEachOrdered(v -> listToPrint.put(v.getKey(), v.getValue()));
        List<String> listFinal = new ArrayList<>();

        for (Map.Entry<Double, List<String>> iterate
                : listToPrint.entrySet()) {
            listFinal.addAll(iterate.getValue());
        }
        return listFinal;
    }
    /**
     *
     */
    public static Map<String, Integer> sortedShowFavourites(final String year,
                  final String genre, final List<Serial> serials, final List<User> users) {
        Map<String, Integer> listToSort = new HashMap<>();
        Map<String, Integer> favouriteMovies = new HashMap<>();

        for (int j = 0; j < serials.size(); j++) {
            int cnt = 0;
            for (int k = 0; k < users.size(); k++) {
                if (users.get(k).getFavoriteMovies().contains(serials.get(j).getTitle())) {
                    cnt++;
                }
            }
            if (cnt != 0) {
                favouriteMovies.put(serials.get(j).getTitle(), cnt);
            }
        }

        if (year == null && genre == null) {
            listToSort.putAll(favouriteMovies);
        } else {
            for (int j = 0; j < serials.size(); j++) {
                String sir =
                        String.valueOf(serials.get(j).getYear());
                if (sir.equals(year) && serials.get(j).getGenres().contains(genre)) {
                    for (Map.Entry<String, Integer> entry
                            : favouriteMovies.entrySet()) {
                        if (entry.getKey().equals(serials.get(j).getTitle())) {
                            if (entry.getValue() != 0) {
                                listToSort.put(serials.get(j).getTitle(), entry.getValue());
                            }
                        }
                    }
                }
            }
        }
       return listToSort;
    }

    /**
     *
     */
    public static Map<String, Integer> sortedShowLongest(final String year,
                  final String genre, final List<Serial> serials, final List<User> users) {

        Map<String, Integer> listToSort = new HashMap<>();
        Map<String, Integer> durationMovie = new HashMap<>();
        for (int j = 0; j < serials.size(); j++) {
            int sum = 0;
            for (int k = 0; k < serials.get(j).getSeasons().size(); k++) {
                sum =
                        sum + serials.get(j).getSeasons().get(k).getDuration();
            }
            durationMovie.put(serials.get(j).getTitle(),
                    sum);
        }
        if(year == null && genre == null) {
            listToSort.putAll(durationMovie);
        } else {
            for (int j = 0; j < serials.size(); j++) {
                String sir =
                        String.valueOf(serials.get(j).getYear());
                if (sir.equals(year) && serials.get(j).getGenres().contains(genre)) {
                    for(Map.Entry<String, Integer> entry :
                            durationMovie.entrySet()) {
                        if(entry.getKey().equals(serials.get(j).getTitle())) {
                            if (entry.getValue() != 0) {
                                listToSort.put(serials.get(j).getTitle()
                                        , entry.getValue());
                            }
                        }
                    }
                }
            }
        }
        return listToSort;
    }

    /**
     *
     */
    public static Map<String, Integer> sortedShowMostViewed(final String year,
                  final String genre, final List<Serial> serials, final List<User> users) {
        Map<String, Integer> mostViews = new HashMap<>();
        for (int j = 0; j < serials.size(); j++) {
            int nrViews = 0;
            for (int k = 0; k < users.size(); k++) {
                if(users.get(k).getHistory().containsKey(serials.get(j).getTitle())) {
                    for(Map.Entry<String, Integer> entry :
                            users.get(k).getHistory().entrySet()) {
                        if(serials.get(j).getTitle().equals(entry.getKey())) {
                            nrViews = nrViews + entry.getValue();
                        }}

                }
            }
            if(nrViews != 0) {
                mostViews.put(serials.get(j).getTitle(), nrViews);
            }
        }
        Map<String, Integer> listToSort = new HashMap<>();
        if(year == null && genre == null) {
            listToSort.putAll(mostViews);
        } else {
            for (int j = 0; j < serials.size(); j++) {
                String sir =
                        String.valueOf(serials.get(j).getYear());
                if (sir.equals(year) && serials.get(j).getGenres().contains(genre)) {
                    for(Map.Entry<String, Integer> entry : mostViews.entrySet()) {
                        if(entry.getKey().equals(serials.get(j).getTitle())) {
                            listToSort.put(serials.get(j).getTitle(), entry.getValue());
                        }
                    }
                }
            }
        }
        return listToSort;
    }

}
