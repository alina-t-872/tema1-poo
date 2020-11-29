package main;

import checker.Checkstyle;
import checker.Checker;
import common.Constants;
import entities.*;
import fileio.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import tasksimplement.Commands;

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
        //inceput implementare

        List<ActorInputData> actorsInputData = new ArrayList<>();
        actorsInputData = input.getActors();
        List<Actor> actors = new ArrayList<>();
        for (int i = 0; i < actorsInputData.size(); i++) {
            Actor actor = new Actor(actorsInputData.get(i));
            actors.add(actor);
        }
        //actors.forEach((v) -> System.out.println(v));

        List<UserInputData> usersInputData = new ArrayList<>();
        usersInputData = input.getUsers();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < usersInputData.size(); i++) {
            User user = new User(usersInputData.get(i));
            users.add(user);
        }
        //users.forEach((v) -> System.out.println(v));

        List<ActionInputData> commandsInputData = new ArrayList<>();
        commandsInputData = input.getCommands();
        List<Action> actions = new ArrayList<>();
        for (int i = 0; i < commandsInputData.size(); i++) {
            Action action = new Action(commandsInputData.get(i));
            actions.add(action);
        }
       //actions.forEach((v) -> System.out.println(v));


        List<MovieInputData> moviesInputData = new ArrayList<>();
        moviesInputData = input.getMovies();
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i< moviesInputData.size(); i++) {
            Movie movie = new Movie(moviesInputData.get(i));
            movies.add(movie);
        }
       //movies.forEach((v) -> System.out.println(v));

        //JSONObject object = fileWriter.writeFile(1,"field","error -> " +
         //       "Sherlock: The Final Problem is already in favourite list");
        //arrayResult.add(object);

        List<SerialInputData> serialsInputData = new ArrayList<>();
        serialsInputData = input.getSerials();
        List<Serial> serials = new ArrayList<>();
        for (int i = 0; i < serialsInputData.size(); i++) {
            Serial serial = new Serial(serialsInputData.get(i));
            serials.add(serial);
        }
       //serials.forEach((v) -> System.out.println(v));

    //Commands command = new Commands();

       for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i).getActionType().equals("command")) {
                if (actions.get(i).getType().equals("favorite")) {
                   for (int j = 0; j < users.size(); j++) {
                       if(users.get(j).getUsername().equals(actions.get(i).getUsername())) {
                           if (users.get(j).getFavoriteMovies().contains(actions.get(i).getTitle())) {
                                JSONObject obj =
                                        fileWriter.writeFile(actions.get(i).getActionId(), "field",
                                        "error -> " + actions.get(i).getTitle() + " is already in favourite list");
                                arrayResult.add(obj);
                           }
                            else {
                                if (users.get(j).getHistory().containsKey(actions.get(i).getTitle())) {
                                    users.get(j).getFavoriteMovies().add(actions.get(i).getTitle());
                                    JSONObject obj = fileWriter.writeFile(actions.get(i).getActionId(), "field",
                                                    "success -> " + actions.get(i).getTitle() + " was added as favourite");
                                    arrayResult.add(obj);
                                } else {
                                    JSONObject obj = fileWriter.writeFile(actions.get(i).getActionId(), "field",
                                            "error -> " + actions.get(i).getTitle() + " is not seen");
                                    arrayResult.add(obj);
                                }
                            }
                       }
                    }
                }
                if (actions.get(i).getType().equals("view")) {
                    for (int j = 0; j < users.size(); j++) {
                        if (users.get(j).getUsername().equals(actions.get(i).getUsername())) {
                            if (users.get(j).getHistory().containsKey(actions.get(i).getTitle())) {
                                users.get(j).getHistory().put(actions.get(i).getTitle(),
                                        ( users.get(j).getHistory().get(actions.get(i).getTitle()) )+ 1);
                                JSONObject obj = fileWriter.writeFile(actions.get(i).getActionId(),
                                                "field", "success -> " + actions.get(i).getTitle() +
                                                " was viewed with total views of " + users.get(j).getHistory().get(actions.get(i).getTitle()));
                                arrayResult.add(obj);
                            } else {
                                users.get(j).getHistory().put(actions.get(i).getTitle(), 1);
                                JSONObject obj = fileWriter.writeFile(actions.get(i).getActionId(),
                                        "field", "success -> " + actions.get(i).getTitle() +
                                                " was viewed with total views of " + users.get(j).getHistory().get(actions.get(i).getTitle()));
                                arrayResult.add(obj);
                            }
                        }
                    }

                }
                if (actions.get(i).getType().equals("rating")) {
                    for (int j = 0; j < users.size(); j++) {
                        if (users.get(j).getUsername().equals(actions.get(i).getUsername())) {
                            for (int k = 0; k < movies.size(); k++) {
                                if (movies.get(k).getTitle().equals(actions.get(i).getTitle())) {
                                    if (users.get(j).getHistory().containsKey(actions.get(i).getTitle())) {
                                        if (movies.get(k).getRating() == 0) {
                                            Movie movie = new Movie(moviesInputData.get(k));
                                            movies.get(k).setRating(movie.calcRating(actions.get(i).getGrade()));
                                            JSONObject object =
                                                    fileWriter.writeFile(actions.get(i).getActionId(),
                                                            "field", "success -> " +
                                                                    actions.get(i).getTitle() + " was rated with " +
                                                                    movies.get(k).getRating() + " by " +
                                                                    users.get(j).getUsername());
                                            arrayResult.add(object);
                                        } else {
                                            JSONObject object =
                                                    fileWriter.writeFile(actions.get(i).getActionId(),
                                                            "field", "error -> "
                                                                    + actions.get(i).getTitle()
                                                                    + " has been already rated");
                                            arrayResult.add(object);
                                        }
                                     } else {
                                        JSONObject object =
                                                fileWriter.writeFile(actions.get(i).getActionId(),
                                                        "field", "error -> " +
                                                                actions.get(i).getTitle() + " is not seen" );
                                        arrayResult.add(object);
                                    }
                                }
                            }
                           for (int k = 0; k < serials.size(); k++) {
                                if (serials.get(k).getTitle().equals(actions.get(i).getTitle())) {
                                    if (users.get(j).getHistory().containsKey(actions.get(i).getTitle())) {
                                        if  (users.get(j).getRatedMovies().containsKey(actions.get(i).getTitle()) &&
                                                users.get(j).getRatedMovies().containsValue(actions.get(i).getSeasonNumber())) {
                                            JSONObject object =
                                                    fileWriter.writeFile(actions.get(i).getActionId(),
                                                            "field", "error -> "
                                                                    + actions.get(i).getTitle()
                                                                    + " has been already rated");
                                            arrayResult.add(object);

                                        } else {
                                            users.get(j).getRatedMovies().put(actions.get(i).getTitle(), actions.get(i).getSeasonNumber());
                                            JSONObject object =
                                                    fileWriter.writeFile(actions.get(i).getActionId(),
                                                            "field", "success -> " +
                                                                    actions.get(i).getTitle() + " was rated with " +
                                                                    Commands.setSeasonRating(serials.get(k), actions.get(i)) +
                                                                    " by " +
                                                                    users.get(j).getUsername());
                                            arrayResult.add(object);
                                        }
                                    } else {
                                        JSONObject object =
                                                fileWriter.writeFile(actions.get(i).getActionId(),
                                                        "field", "error -> " +
                                                                actions.get(i).getTitle() + " is not seen" );
                                        arrayResult.add(object);

                                    }
                                }
                            }
                        }
                    }

                }
            }
            if (actions.get(i).getActionType().equals("query")) {
                if (actions.get(i).getObjectType().equals("actors")) {
                    if(actions.get(i).getCriteria().equals("average")) {
                            Map<String, Double> copyMovies = new HashMap<>();
                            if (actions.get(i).getSortType().equals("asc"))  { //daca
                                // este crescator
                                for (int j = 0; j < actors.size(); j++) {
                                    double sum = 0;
                                    int cnt = 0;
                                    for (int k = 0; k < actors.get(j).getFilmography().size(); k++) {

                                            for (int q = 0; q < movies.size(); q++) {
                                                if (movies.get(q).getTitle().equals(actors.get(j).getFilmography().get(k))) {
                                                    sum = sum + movies.get(q).getRating();
                                                    if(movies.get(q).getRating() != 0) {
                                                        cnt++;
                                                    }
                                                    //System.out.println
                                                    // (movies.get(q).getRating());
                                                    break;
                                                }
                                            }

                                            for (int q = 0; q < serials.size(); q++) {
                                                //System.out.println("works");
                                                if (serials.get(q).getTitle().equals(actors.get(j).getFilmography().get(k))) {
                                                    //System.out.println("works");
                                                    sum =
                                                            sum + Commands.calcRating(serials.get(q));
                                                    if (Commands.calcRating(serials.get(q)) != 0) {
                                                        cnt++;
                                                    }
                                                   // System.out.println
                                                    // (Commands.calcRating(serials.get(q)));
                                                    break;
                                                }
                                            }
                                        //}
                                    }
                                    sum = sum / cnt;
                                   // System.out.println(sum);
                                    copyMovies.put(actors.get(j).getName(), sum);
                                }
                                List<Map.Entry<String, Double> > sortActors = new LinkedList<Map.Entry<String,
                                                Double> >(copyMovies.entrySet());
                                Collections.sort(sortActors,
                                        new Comparator<Map.Entry<String, Double> >() {
                                    public int compare(Map.Entry<String, Double> o1,
                                                       Map.Entry<String, Double> o2)
                                    {
                                        return (o1.getValue()).compareTo(o2.getValue());
                                    }
                                });
                                //System.out.println(sortActors);
                                List<Map.Entry<String, Double> > sortFinal =
                                        new LinkedList<Map.Entry<String,
                                        Double> >(copyMovies.entrySet());
                                for (int k = 0; k < actions.get(i).getNumber(); k++) {
                                    sortFinal.add(sortActors.get(k));
                                }
                                JSONObject object =
                                        fileWriter.writeFile(actions.get(i).getActionId(),
                                                "field", "Query result: " +
                                                        sortFinal);
                                arrayResult.add(object);

                            } else {   //daca este descrescators
                               /* for (int j = 1; j <= actions.get(i)
                               .getNumber(); j++) {
                                

                                }*/
                            }

                    }
                    if(actions.get(i).getCriteria().equals("awards")) {

                    }
                    if(actions.get(i).getCriteria().equals("filter_description")) {

                    }
                }
                if (actions.get(i).getObjectType().equals("movies")) {
                    if(actions.get(i).getCriteria().equals("ratings")) {

                    }
                    if(actions.get(i).getCriteria().equals("favorite")) {
                       for (List<String> first : input.getCommands().get(i).getFilters()) {
                           for (String second : first) {
                               if(first != null) {
                                   System.out.println(second);
                               }
                           }
                       }


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
                        TreeMap<String, Integer> sorted = new TreeMap<>();
                        sorted.putAll(favouriteMovies);
                        favouriteMovies.putAll(sorted);
                        //System.out.println(favouriteMovies);
                        List<Map.Entry<String, Integer> > sortFavourite = new LinkedList<Map.Entry<String, Integer> >(favouriteMovies.entrySet());
                        Collections.sort(sortFavourite,
                                new Comparator<Map.Entry<String, Integer> >() {
                                    public int compare(Map.Entry<String,
                                            Integer> o1, Map.Entry<String, Integer> o2)
                                    {
                                        return (o1.getValue()).compareTo(o2.getValue());
                                    }
                                });
                       // System.out.println(sortFavourite);
                        List<Map.Entry<String, Integer> > sortFinal = new LinkedList<Map.Entry<String, Integer> >();
                       /* if (actions.get(i).getSortType().equals("asc"))  {
                            for(int q = 0; q < actions.get(i).getNumber(); q++) {
                                sortFinal.add(sortFavourite.get(q));
                            }
                        } else {
                            for(int q = actions.get(i).getNumber() - 1; q>=0; q--) {
                                sortFinal.add(sortFavourite.get(q));
                            }
                        }*/
                        JSONObject object =
                                fileWriter.writeFile(actions.get(i).getActionId(),
                                        "field", "Query result: " +
                                                sortFinal);
                        arrayResult.add(object);
                    }
                    if(actions.get(i).getCriteria().equals("longest")) {

                    }
                    if(actions.get(i).getCriteria().equals("most_viewed")) {

                    }

                }
                if (actions.get(i).getObjectType().equals("shows")) {
                    if(actions.get(i).getCriteria().equals("ratings")) {

                    }
                    if(actions.get(i).getCriteria().equals("favorite")) {

                    }
                    if(actions.get(i).getCriteria().equals("longest")) {

                    }
                    if(actions.get(i).getCriteria().equals("most_viewed")) {

                    }

                }
                if (actions.get(i).getObjectType().equals("users") &&
                        actions.get(i).getCriteria().equals("num_ratings")) {

                }

            }
            if (actions.get(i).getActionType() == "recommendation") {

            }


        }
        fileWriter.closeJSON(arrayResult);
    }
}
