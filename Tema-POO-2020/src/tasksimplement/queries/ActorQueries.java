package tasksimplement.queries;

import entities.Actor;
import fileio.ActionInputData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ActorQueries {
    private ActorQueries() { }

    /**
     *Here are implemented methods for the actor queries
     * actorAwards creates a map with actors and their number of awards, but only those who have all
     * the awards mentioned in the filter will be in the final list that is returned and which
     * will be sorted
     */
    public static Map<String, Integer> actorAwards(final List<Actor> actors,
                                                   final ActionInputData actionInputData) {
        Map<String, Integer> listToSort = new HashMap<>();
        Map<String, Integer> actorAwards = new HashMap<>();

        for (int j = 0; j < actors.size(); j++) {
            if (actors.get(j).getAwards().size() != 0) {

                actorAwards.put(actors.get(j).getName(), actors.get(j).getAwards().size());
            }
        }
        if (actionInputData.getFilters().get(3).size() == 0) {
            listToSort.putAll(actorAwards);
        } else {
            for (int j = 0; j < actors.size(); j++) {
                int cnt = 0;
                int dimFilter = actionInputData.getFilters().get(3).size();
                for (int k = 0; k < dimFilter; k++) {
                    String awardFilter = actionInputData.getFilters().get(3).get(k);

                    if (actors.get(j).getAwards().containsKey(awardFilter)) {
                        cnt++;
                    }
                }

                if (cnt == dimFilter) {
                    actorAwards.put(actors.get(j).getName(),
                            actors.get(j).getAwards().size());
                }
            }
        }
        return listToSort;
    }

    /**
     *filterDescription returns a list of strings representing the actors that have in their
     * description all the keywords given in the filter and which will be sorted
     */
    public static ArrayList<String> filterDescription(final List<Actor> actors,
                                           final ActionInputData actionInputData) {
        ArrayList<String> filterWords = new ArrayList<>();
        int dim = actionInputData.getFilters().get(2).size();

        for (int j = 0; j < actors.size(); j++) {
            int ok = 1;
            for (int k = 0; k < dim; k++) {

                String keyWord = new String();
                keyWord = actionInputData.getFilters().get(2).get(k);

                if (!actors.get(j).getCareerDescription().contains(keyWord)) {
                    ok = 0;
                }
            }

            if (ok == 1) {
                filterWords.add(actors.get(j).getName());
            }
        }
        return filterWords;
    }
}
