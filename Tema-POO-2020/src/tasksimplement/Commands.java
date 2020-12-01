package tasksimplement;

import entities.*;
import fileio.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public final class Commands {
    private Commands() { }


    /**
     *
     */
    public static double setSeasonRating(final Serial serial,
                                         final Action action) {
        int size = serial.getNumberOfSeasons();
        for (int i = 1; i <= size; i++) {
            if (i == action.getSeasonNumber()) {
                serial.getSeasons().get(i - 1).getRatings().add(action.getGrade());
            }
        }
        return action.getGrade();
    }

    /**
     *
     */
    public static double calcRating(final Serial serial) {
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
        serial.setGeneralRating(sumSerial);
        return sumSerial;
    }

    /**
     *
     */
    public static void favourite(final List<User> users, final Action action,
                                 final JSONArray arrayResult,
                                 final Writer fileWriter) throws IOException {
        for (int j = 0; j < users.size(); j++) {
            if (users.get(j).getUsername().equals(action.getUsername())) {
                if (users.get(j).getFavoriteMovies().contains(action
                        .getTitle())) {

                    JSONObject obj = fileWriter
                            .writeFile(action.getActionId(), "field",
                                    "error -> "
                                            + action.getTitle()
                                            + " is already in favourite list");
                    arrayResult.add(obj);
                } else {
                    if (users.get(j).getHistory()
                            .containsKey(action.getTitle())) {
                        users.get(j).getFavoriteMovies().add(action.getTitle());
                        JSONObject obj = fileWriter
                                .writeFile(action.getActionId(), "field",
                                        "success -> "
                                                + action.getTitle()
                                                + " was added as favourite");
                        arrayResult.add(obj);
                    } else {
                        JSONObject obj = fileWriter
                                .writeFile(action.getActionId(), "field",
                                        "error -> "
                                                + action.getTitle()
                                                + " is not seen");
                        arrayResult.add(obj);
                    }
                }
            }
        }
    }

    /**
     *
     */
    public static void view(final List<User> users, final Action action,
                                 final JSONArray arrayResult,
                                 final Writer fileWriter) throws IOException {
        for (int j = 0; j < users.size(); j++) {
            if (users.get(j).getUsername().equals(action.getUsername())) {
                if (users.get(j).getHistory().containsKey(action.getTitle())) {
                    users.get(j).getHistory().put(action.getTitle(),
                            ( users.get(j).getHistory().get(action.getTitle()) )+ 1);

                    JSONObject obj = fileWriter.writeFile(action.getActionId(),
                            "field", "success -> " + action.getTitle() +
                                    " was viewed with total views of " + users.get(j).getHistory().get(action.getTitle()));
                    arrayResult.add(obj);
                } else {
                    users.get(j).getHistory().put(action.getTitle(), 1);
                    JSONObject obj = fileWriter.writeFile(action.getActionId(),
                            "field", "success -> " + action.getTitle() +
                                    " was viewed with total views of " + users.get(j).getHistory().get(action.getTitle()));
                    arrayResult.add(obj);
                }
            }
        }
    }

    /**
     *
     */
    public static void rating(final List<User> users, final Action action,
                              final List<Movie> movies, final List<Serial> serials,
                              final JSONArray arrayResult,
                              final List<MovieInputData> moviesInputData,
                              final Writer fileWriter) throws IOException {
        for (int j = 0; j < users.size(); j++) {
            if (users.get(j).getUsername().equals(action.getUsername())) {
                for (int k = 0; k < movies.size(); k++) {
                    if (movies.get(k).getTitle().equals(action.getTitle())) {
                        if (users.get(j).getHistory()
                                .containsKey(action.getTitle())) {
                            if (!users.get(j).getRatedMovies()
                                    .containsKey(action.getTitle())) {
                                Movie movie = new Movie(moviesInputData.get(k));
                                movies.get(k).setRating(movie
                                        .calcRating(action.getGrade()));
                                JSONObject object = fileWriter
                                        .writeFile(action.getActionId(),
                                                "field", "success -> "
                                                        + action.getTitle()
                                                        + " was rated with " + movies.get(k)
                                                        .getRating()
                                                        + " by "
                                                        + users.get(j).getUsername());
                                arrayResult.add(object);
                            } else {
                                JSONObject object =
                                        fileWriter
                                                .writeFile(action.getActionId(),
                                                        "field",
                                                        "error -> "
                                                                + action.getTitle()
                                                                + " has been already rated");
                                arrayResult.add(object);
                            }
                        } else {
                            JSONObject object =
                                    fileWriter.writeFile(action.getActionId(),
                                            "field", "error -> "
                                                    + action.getTitle()
                                                    + " is not seen");
                            arrayResult.add(object);
                        }
                    }
                }
                for (int k = 0; k < serials.size(); k++) {
                    if (serials.get(k).getTitle().equals(action.getTitle())) {
                        if (users.get(j).getHistory().containsKey(action.getTitle())) {
                            if (users.get(j).getRatedMovies()
                                    .containsKey(action.getTitle())
                                    && users.get(j)
                                    .getRatedMovies()
                                    .containsValue(action.getSeasonNumber())) {
                                JSONObject object =
                                        fileWriter
                                                .writeFile(action.getActionId(),
                                                        "field", "error -> "
                                                                + action.getTitle()
                                                                + " has been already rated");
                                arrayResult.add(object);

                            } else {
                                users.get(j)
                                        .getRatedMovies()
                                        .put(action.getTitle(), action.getSeasonNumber());
                                JSONObject object =
                                        fileWriter
                                                .writeFile(action.getActionId(),
                                                        "field", "success -> "
                                                                + action.getTitle()
                                                                + " was rated with "
                                                                + Commands
                                                                .setSeasonRating(serials.get(k),
                                                                        action)
                                                                + " by "
                                                                + users.get(j)
                                                                .getUsername());
                                arrayResult.add(object);
                            }
                        } else {
                            JSONObject object =
                                    fileWriter.writeFile(action.getActionId(),
                                            "field", "error -> "
                                                    + action.getTitle()
                                                    + " is not seen");
                            arrayResult.add(object);

                        }
                    }
                }
            }
        }


    }

}
