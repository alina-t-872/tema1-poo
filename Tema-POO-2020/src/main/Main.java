package main;

import checker.Checkstyle;
import checker.Checker;
import common.Constants;
import entities.*;
import fileio.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import tasksimplement.Commands;
import tasksimplement.queries.MainQueries;
import tasksimplement.Recommendations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * Call the main checker and the coding style checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH, Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();

    }


    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();

        //TODO add here the entry point to your implementation
        List<ActorInputData> actorsInputData = new ArrayList<>();
        actorsInputData = input.getActors();
        List<Actor> actors = new ArrayList<>();
        for (int i = 0; i < actorsInputData.size(); i++) {
            Actor actor = new Actor(actorsInputData.get(i));
            actors.add(actor);
        }

        List<UserInputData> usersInputData = new ArrayList<>();
        usersInputData = input.getUsers();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < usersInputData.size(); i++) {
            User user = new User(usersInputData.get(i));
            users.add(user);
        }

        List<ActionInputData> commandsInputData = new ArrayList<>();
        commandsInputData = input.getCommands();
        List<Action> actions = new ArrayList<>();
        for (int i = 0; i < commandsInputData.size(); i++) {
            Action action = new Action(commandsInputData.get(i));
            actions.add(action);
        }

        List<MovieInputData> moviesInputData = new ArrayList<>();
        moviesInputData = input.getMovies();
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < moviesInputData.size(); i++) {
            Movie movie = new Movie(moviesInputData.get(i));
            movies.add(movie);
        }

        List<SerialInputData> serialsInputData = new ArrayList<>();
        serialsInputData = input.getSerials();
        List<Serial> serials = new ArrayList<>();
        for (int i = 0; i < serialsInputData.size(); i++) {
            Serial serial = new Serial(serialsInputData.get(i));
            serials.add(serial);
        }


        for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i).getActionType().equals("command")) {
                if (actions.get(i).getType().equals("favorite")) {
                    Commands.favourite(users, actions.get(i), arrayResult, fileWriter);
                }
                if (actions.get(i).getType().equals("view")) {
                    Commands.view(users, actions.get(i), arrayResult, fileWriter);
                }
                if (actions.get(i).getType().equals("rating")) {
                    Commands.rating(users, actions.get(i), movies, serials, arrayResult,
                            moviesInputData, fileWriter);
                }
            }
            if (actions.get(i).getActionType().equals("query")) {
                if (actions.get(i).getObjectType().equals("actors")) {
                    if (actions.get(i).getCriteria().equals("average")) {

                    }
                    if (actions.get(i).getCriteria().equals("awards")) {

                    }
                    if (actions.get(i).getCriteria().equals("filter_description")) {

                    }
                }
                if (actions.get(i).getObjectType().equals("movies")) {
                    if (actions.get(i).getCriteria().equals("ratings")) {
                        MainQueries.movieRatings(users, movies, arrayResult, actions.get(i),
                                input.getCommands().get(i), fileWriter);
                    }
                    if (actions.get(i).getCriteria().equals("favorite")) {
                        MainQueries.movieFavorite(users, movies, arrayResult, actions.get(i),
                                input.getCommands().get(i), fileWriter);
                    }
                    if (actions.get(i).getCriteria().equals("longest")) {
                        MainQueries.movieLong(users, movies, arrayResult,
                                input.getCommands().get(i), fileWriter);
                    }
                    if (actions.get(i).getCriteria().equals("most_viewed")) {
                        MainQueries.movieMostViewed(users, movies, arrayResult, actions.get(i),
                                input.getCommands().get(i), fileWriter);
                    }

                }
                if (actions.get(i).getObjectType().equals("shows")) {
                    if (actions.get(i).getCriteria().equals("ratings")) {
                        MainQueries.showsRatings(users, serials, arrayResult, actions.get(i),
                                input.getCommands().get(i), fileWriter);
                    }
                    if (actions.get(i).getCriteria().equals("favorite")) {
                        MainQueries.showsFavorite(users, serials, arrayResult, actions.get(i),
                                input.getCommands().get(i), fileWriter);
                    }
                    if (actions.get(i).getCriteria().equals("longest")) {
                        MainQueries.showsLong(users, serials, arrayResult, actions.get(i),
                                input.getCommands().get(i), fileWriter);
                    }
                    if (actions.get(i).getCriteria().equals("most_viewed")) {
                        MainQueries.showsMostViewed(users, serials, arrayResult, actions.get(i),
                                input.getCommands().get(i), fileWriter);
                    }
                }
                if (actions.get(i).getObjectType().equals("users")
                        && actions.get(i).getCriteria().equals("num_ratings")) {

                }
            }
            if (actions.get(i).getActionType().equals("recommendation")) {
                if (actions.get(i).getType().equals("standard")) {
                    Recommendations.findMovie(users, movies, arrayResult, fileWriter,
                            actions.get(i));

                }
                if (actions.get(i).getType().equals("search")) {
                    Recommendations.search(users, actions.get(i), movies, serials, arrayResult,
                            fileWriter);
                }
                if (actions.get(i).getType().equals("favorite")) {
                    Recommendations.favorite(users, actions.get(i), movies, serials, arrayResult,
                            fileWriter);
                }

            }
        }
        fileWriter.closeJSON(arrayResult);
    }
}
